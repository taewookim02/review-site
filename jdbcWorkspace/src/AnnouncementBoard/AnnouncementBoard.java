package AnnouncementBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.Main;
import util.JDBCTemplate;
import util.TablePrinter;

public class AnnouncementBoard {

	public void printMenu() throws Exception {

		System.out.println("==== 공지사항 게시판 ====");
		System.out.println("1. 공지사항 등록 (관리자)");
		System.out.println("2. 공지사항 삭제 (관리자)");
		System.out.println("3. 공지사항 제목 수정 (관리자)");
		System.out.println("4. 공지사항 내용 수정 (관리자)");
		System.out.println("5. 공지사항 전체 조회 (최신순)");
		System.out.println("6. 공지사항 상세조회 (번호)");
		System.out.println("7. 공지사항 상세조회 (생성일자)");
		System.out.println("8. 이전으로 돌아가기");

		System.out.print("번호 입력 : ");
		String menu = Main.SC.nextLine();

		switch (menu) {
		case "1":
			write();
			break;
		case "2":
			delete();
			break;
		case "3":
			editTitle();
			break;
		case "4":
			editContent();
			break;
		case "5":
			selectAllAnnouncement();
			break;
		case "6":
			selectAnnouncementNo();
			break;
		case "7":
			selectAnnouncementEnrollDate();
			break;
		case "8":
			System.out.println("====================");
			System.out.println("이전 메뉴로 돌아갑니다.");
			System.out.println("====================");
			return;
		default:
			System.out.println("====================");
			System.out.println("잘못 입력하셨습니다.");
			System.out.println("====================");
			break;
		}

	}

	private void write() throws Exception {

		if (Main.loginMember == null) {
			System.out.println("=======================");
			System.err.println("관리자 계정으로 로그인 바랍니다.");
			System.out.println("=======================");
			return;
		}

		if (Main.loginMember.getNo().equals("1")) {

			Connection conn = JDBCTemplate.getConn();

			String sql = "INSERT INTO ANNOUNCEMENT_BOARD(NO, TITLE, CONTENT, WRITER_NO) VALUES(SEQ_ANNOUNCEMENT_BOARD_NO.NEXTVAL , ? , ?, '1')";

			System.out.print("제목 : ");
			String title = Main.SC.nextLine();
			System.out.print("내용 : ");
			String content = Main.SC.nextLine();

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);

			int result = pstmt.executeUpdate();

			if (result != 1) {
				System.out.println("====================");
				System.out.println("공지사항 등록 실패.");
				System.out.println("====================");
				return;
			}
			System.out.println("====================");
			System.out.println("공지사항 등록 완료");
			System.out.println("====================");

		} else {
			System.out.println("====================");
			System.out.println("관리자만 공지사항 작성 가능.");
			System.out.println("====================");
			return;
		}

	}

	private void delete() throws Exception {

		if (Main.loginMember.getNo().equals("1")) {

			Connection conn = JDBCTemplate.getConn();

			String sql = "UPDATE ANNOUNCEMENT_BOARD SET DEL_YN = 'Y' WHERE NO = ? AND DEL_YN = 'N'";
			System.out.print("삭제할 공지사항 번호 : ");
			String no = Main.SC.nextLine();

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);

			int result = pstmt.executeUpdate();

			if (result != 1) {
				System.out.println("====================");
				System.out.println("공지사항 삭제 실패.");
				System.out.println("====================");
				return;
			}
			System.out.println("====================");
			System.out.println("공지사항 삭제 완료.");
			System.out.println("====================");

		} else {
			System.out.println("====================");
			System.out.println("일반 회원은 삭제할 수 없습니다.");
			System.out.println("====================");
			return;
		}

	}

	private void editTitle() throws Exception {

		if (Main.loginMember.getAdmin_yn().equals("Y")) {

			Connection conn = JDBCTemplate.getConn();

			String sql = "UPDATE ANNOUNCEMENT_BOARD SET TITLE = ? WHERE NO = ? AND DEL_YN = 'N'";
			System.out.print("수정할 공지사항 번호 : ");
			String no = Main.SC.nextLine();
			System.out.print("수정할 제목 : ");
			String title = Main.SC.nextLine();

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, no);

			int result = pstmt.executeUpdate();

			if (result != 1) {
				System.out.println("====================");
				System.out.println("공지사항 제목 수정 실패.");
				System.out.println("====================");
				return;
			}
			System.out.println("====================");
			System.out.println("공지사항 제목 수정 완료.");
			System.out.println("====================");

		}

	}

	private void editContent() throws Exception {

		if (Main.loginMember.getAdmin_yn().equals("Y")) {

			Connection conn = JDBCTemplate.getConn();

			String sql = "UPDATE ANNOUNCEMENT_BOARD SET CONTENT = ? WHERE NO = ? AND DEL_YN = 'N'";
			System.out.print("수정할 공지사항 번호 : ");
			String no = Main.SC.nextLine();
			System.out.print("수정할 내용 : ");
			String content = Main.SC.nextLine();

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setString(2, no);

			int result = pstmt.executeUpdate();

			if (result != 1) {
				System.out.println("====================");
				System.out.println("공지사항 내용 수정 실패.");
				System.out.println("====================");
				return;
			}
			System.out.println("====================");
			System.out.println("공지사항 내용 수정 완료.");
			System.out.println("====================");

		}

	}

	private void selectAllAnnouncement() throws Exception {

		Connection conn = JDBCTemplate.getConn();

		String sql = "SELECT NO, TITLE, ENROLL_DATE FROM ANNOUNCEMENT_BOARD WHERE DEL_YN = 'N' ORDER BY NO DESC";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		List<AnnouncementBoardVo> voList = new ArrayList<AnnouncementBoardVo>();
		AnnouncementBoardVo Vo = null;
		while (rs.next()) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String enrollDate = rs.getString("ENROLL_DATE");

			Vo = new AnnouncementBoardVo(no, title, null, null, enrollDate, null);
			voList.add(Vo);
		}

		TablePrinter.printRecordsVertically(voList, new String[] { "no", "title", "enrollDate" },
				new String[] { "번호", "제목", "생성일자" });

	}

	private void selectAnnouncementNo() throws Exception {

		Connection conn = JDBCTemplate.getConn();

		String sql = "SELECT A.NO , TITLE , CONTENT , M.NICK , ENROLL_DATE FROM ANNOUNCEMENT_BOARD A JOIN MEMBER M ON M.NO = A.WRITER_NO WHERE A.NO = ? AND A.DEL_YN = 'N'";

		System.out.print("공지사항 번호 선택 : ");
		String no = Main.SC.nextLine();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);

		ResultSet rs = pstmt.executeQuery();

		AnnouncementBoardVo vo = null;
		List<AnnouncementBoardVo> voList = new ArrayList<AnnouncementBoardVo>();
		if (rs.next()) {
			String no2 = rs.getString("NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String nick = rs.getString("NICK");
			String enrollDate = rs.getString("ENROLL_DATE");

			vo = new AnnouncementBoardVo();
			vo.setNo(no2);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setWriterNo(nick);
			vo.setEnrollDate(enrollDate);

			voList.add(vo);

			TablePrinter.printRecordsVertically(voList, new String[] { "no", "title", "content", "writerNo", "enrollDate" },
					new String[] { "번호", "제목", "내용", "작성자", "생성일자" });

		} else if (vo == null) {
			System.out.println("=======================");
			System.out.println("공지사항 조회 실패.");
			System.out.println("공지사항 번호 확인바람.");
			System.out.println("=======================");
		}

	}

	private void selectAnnouncementEnrollDate() throws Exception {

		Connection conn = JDBCTemplate.getConn();

		String sql = "SELECT NO, TITLE, CONTENT, ENROLL_DATE FROM ANNOUNCEMENT_BOARD WHERE DEL_YN = 'N' AND TO_CHAR(ENROLL_DATE, 'YYYY\"년\" MM\"월\" DD\"일\"') LIKE '%' || ? || '%' ORDER BY NO ";

		System.out.print("조회할 날짜 (0000년 00월 00일) : ");
		String no = Main.SC.nextLine();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);

		ResultSet rs = pstmt.executeQuery();

		List<AnnouncementBoardVo> voList = new ArrayList<AnnouncementBoardVo>();
		AnnouncementBoardVo vo = null;
		while (rs.next()) {
			String no2 = rs.getString("NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String enrollDate = rs.getString("ENROLL_DATE");

			vo = new AnnouncementBoardVo(no2, title, content, null, enrollDate, null);
			voList.add(vo);
		}
		if (vo == null) {
			System.out.println("=======================");
			System.out.println("공지사항 조회 실패.");
			System.out.println("날짜 재확인 바람.");
			System.out.println("=======================");
		} else {
			TablePrinter.printRecordsVertically(voList, new String[] { "no", "title", "content", "enrollDate" },
					new String[] { "번호", "제목", "내용", "생성일자" });
		}

	}

}
