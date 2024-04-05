package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.Main;
import oracle.net.aso.b;
import util.JDBCTemplate;
import util.TablePrinter;

public class BoardController {

	public void printBoardMenu() throws Exception {

		System.out.println("==== 자유 게시판 ====");
		System.out.println("1. 게시물 작성");
		System.out.println("2. 게시물 삭제 (작성자 또는 관리자)");
		System.out.println("3. 게시물 제목 수정 (작성자 또는 관리자)");
		System.out.println("4. 게시물 내용 수정 (작성자 또는 관리자)");
		System.out.println("5. 게시물 목록 조회 (최신순)");
		System.out.println("6. 게시물 작성자명으로 조회");
		System.out.println("7. 내가 작성한 게시물 조회");
		System.out.println("8. 이전으로 돌아가기");
		System.out.print("번호 입력 : ");

		String num = Main.SC.nextLine();

		switch (num) {
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
			selectBoardList();
			break;
		case "6":
			selectByWriterName();
			break;
		case "7":
			selectMyBoard();
			break;
		case "8":
			return;
		default:
			System.out.println("==============");
			System.out.println("잘못 입력하셨습니다.");
			System.out.println("==============");
		}

	}

	private void write() throws Exception {

		if (Main.loginMember == null) {
			System.out.println("====================");
			System.out.println("로그인이 필요합니다.");
			System.out.println("====================");
			return;
		}

		Connection conn = JDBCTemplate.getConn();

		String sql = "INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) VALUES(SEQ_BOARD_NO.NEXTVAL , ? , ?, ?)";

		System.out.print("게시글 제목 : ");
		String title = Main.SC.nextLine();
		System.out.print("게시글 내용 : ");
		String content = Main.SC.nextLine();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, Main.loginMember.getNo());

		int result = pstmt.executeUpdate();

		if (result != 1) {
			System.out.println("====================");
			System.out.println("게시글 작성 실패.");
			System.out.println("====================");
			return;
		}
		System.out.println("====================");
		System.out.println("게시글 작성 완료.");
		System.out.println("====================");

	}

	private void delete() throws Exception {

		if (Main.loginMember == null) {
			System.out.println("===================");
			System.out.println("로그인이 필요합니다.");
			System.out.println("===================");
			return;
		}

		if (Main.loginMember.getNo().equals("1")) {
			adminDelete();
		} else {
			userDelete();
		}

	}

	private void userDelete() throws Exception {
		Connection conn = JDBCTemplate.getConn();

		String sql = "UPDATE BOARD SET DEL_YN = 'Y' WHERE NO = ? AND WRITER_NO = ? AND DEL_YN = 'N'";
		System.out.print("삭제할 게시물 번호 : ");
		String no = Main.SC.nextLine();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		pstmt.setString(2, Main.loginMember.getNo());

		int result = pstmt.executeUpdate();

		if (result != 1) {
			System.out.println("============================================");
			System.out.println("자신의 게시물 또는 삭제되지 않은 게시물만 삭제 가능.");
			System.out.println("============================================");
			return;
		}
		System.out.println("=================");
		System.out.println("게시물이 삭제 완료.");
		System.out.println("=================");
	}

	private void adminDelete() throws Exception {
		Connection conn = JDBCTemplate.getConn();

		String sql = "UPDATE BOARD SET DEL_YN = 'Y' WHERE NO = ?  AND DEL_YN = 'N'";
		System.out.print("삭제할 게시물 번호 : ");
		String no = Main.SC.nextLine();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		;

		int result = pstmt.executeUpdate();

		if (result != 1) {
			System.out.println("===============================");
			System.out.println("삭제되지 않은 게시물만 삭제 가능.");
			System.out.println("===============================");
			return;
		}
		System.out.println("==================");
		System.out.println("게시물 삭제 완료.");
		System.out.println("==================");
	}

	private void editTitle() throws Exception {
		if (Main.loginMember == null) {
			System.out.println("===================");
			System.out.println("로그인이 필요합니다.");
			System.out.println("===================");
			return;
		}

		if (Main.loginMember.getNo().equals("1")) {
			adminEditTitle();
		} else {
			userEditTitle();
		}

	}

	private void userEditTitle() throws Exception {

		Connection conn = JDBCTemplate.getConn();

		String sql = "UPDATE BOARD SET TITLE = ? WHERE NO = ? AND DEL_YN = 'N' AND WRITER_NO = ?";

		System.out.print("수정할 게시물 번호 : ");
		String no = Main.SC.nextLine();
		System.out.print("수정할 제목 : ");
		String title = Main.SC.nextLine();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, no);
		pstmt.setString(3, Main.loginMember.getNo());

		int result = pstmt.executeUpdate();

		if (result != 1) {
			System.out.println("=============================================");
			System.out.println("자신의 게시물 또는 삭제되지 않은 게시물만 변경 가능.");
			System.out.println("=============================================");
			return;
		}
		System.out.println("==========================");
		System.out.println("게시물 제목 변경 완료.");
		System.out.println("==========================");

	}

	private void adminEditTitle() throws Exception {

		Connection conn = JDBCTemplate.getConn();

		String sql = "UPDATE BOARD SET TITLE = ? WHERE NO = ? AND DEL_YN = 'N'";

		System.out.print("수정할 게시물 번호 : ");
		String no = Main.SC.nextLine();
		System.out.print("수정할 제목 : ");
		String title = Main.SC.nextLine();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, no);

		int result = pstmt.executeUpdate();

		if (result != 1) {
			System.out.println("===============================");
			System.out.println("삭제되지 않은 게시물만 변경 가능.");
			System.out.println("===============================");
			return;
		}
		System.out.println("============================");
		System.out.println("게시물 제목 변경 완료.");
		System.out.println("============================");

	}

	private void editContent() throws Exception {
		if (Main.loginMember == null) {
			System.out.println("====================");
			System.out.println("로그인이 필요합니다.");
			System.out.println("====================");
			return;
		}

		if (Main.loginMember.getNo().equals("1")) {
			adminEditContent();
		} else {
			userEditContent();
		}

	}

	private void userEditContent() throws Exception {

		Connection conn = JDBCTemplate.getConn();

		String sql = "UPDATE BOARD SET CONTENT = ? WHERE NO = ? AND DEL_YN = 'N' AND WRITER_NO = ?";

		System.out.print("수정할 게시물 번호 : ");
		String no = Main.SC.nextLine();
		System.out.print("수정할 내용 : ");
		String content = Main.SC.nextLine();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, content);
		pstmt.setString(2, no);
		pstmt.setString(3, Main.loginMember.getNo());

		int result = pstmt.executeUpdate();

		if (result != 1) {
			System.out.println("=============================================");
			System.out.println("자신의 게시물 또는 삭제되지 않은 게시물만 변경 가능.");
			System.out.println("=============================================");
			return;
		}
		System.out.println("============================");
		System.out.println("게시물 내용 변경 완료.");
		System.out.println("============================");
	}

	private void adminEditContent() throws Exception {

		Connection conn = JDBCTemplate.getConn();

		String sql = "UPDATE BOARD SET CONTENT = ? WHERE NO = ? AND DEL_YN = 'N'";

		System.out.print("수정할 게시물 번호 : ");
		String no = Main.SC.nextLine();
		System.out.print("수정할 내용 : ");
		String content = Main.SC.nextLine();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, content);
		pstmt.setString(2, no);

		int result = pstmt.executeUpdate();

		if (result != 1) {
			System.out.println("===============================");
			System.out.println("삭제되지 않은 게시물만 변경 가능.");
			System.out.println("===============================");
			return;
		}
		System.out.println("============================");
		System.out.println("게시물 내용 변경 완료.");
		System.out.println("============================");

	}

	private void selectBoardList() throws Exception {

		Connection conn = JDBCTemplate.getConn();

		String sql = "SELECT B.NO, B.TITLE, B.ENROLL_DATE, M.NICK FROM BOARD B JOIN MEMBER M ON B.WRITER_NO = M.NO WHERE DEL_YN = 'N' ORDER BY M.NO";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		List<BoardVo> voList = new ArrayList<BoardVo>();
		BoardVo bVo = null;
		while (rs.next()) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String nick = rs.getString("NICK");
			String enrollDate = rs.getString("ENROLL_DATE");

			bVo = new BoardVo(no, title, null, nick, enrollDate, null);
			voList.add(bVo);
		}


		TablePrinter.printTable(voList, new String[] { "no", "title","writerNo", "enrollDate" },
				new String[] { "번호", "제목", "작성자", "생성일자" });
		

	}

	private void selectByWriterName() throws Exception {

		Connection conn = JDBCTemplate.getConn();

		String sql = "SELECT B.NO, B.TITLE, B.WRITER_NO, B.ENROLL_DATE FROM BOARD B JOIN MEMBER M ON M.NO = B.WRITER_NO WHERE M.NICK = ? AND DEL_YN = 'N'";

		System.out.print("찾을 작성자 닉네임 : ");
		String nick = Main.SC.nextLine();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, nick);

		ResultSet rs = pstmt.executeQuery();

		List<BoardVo> voList = new ArrayList<BoardVo>();
		BoardVo bVo = null;
		while (rs.next()) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String writertNo = rs.getString("WRITER_NO");
			String enrollDate = rs.getString("ENROLL_DATE");

			bVo = new BoardVo();
			bVo.setNo(no);
			bVo.setWriterNo(writertNo);
			bVo.setTitle(title);
			bVo.setEnrollDate(enrollDate);
			voList.add(bVo);

		}
		if (bVo == null) {
			System.out.println("==================");
			System.out.println("게시물 조회 실패.");
			System.out.println("정확하게 입력 바람.");
			System.out.println("==================");
		} else {
			TablePrinter.printTable(voList, new String[] { "no", "writerNo", "title", "enrollDate" },
					new String[] { "번호", "작성자 번호", "제목", "생성일자" });
		}

	}

	private void selectMyBoard() throws Exception {

		if (Main.loginMember == null) {
			System.out.println("====================");
			System.out.println("로그인이 필요 합니다.");
			System.out.println("====================");
			return;
		}

		Connection conn = JDBCTemplate.getConn();

		String sql = "SELECT NO, TITLE, ENROLL_DATE FROM BOARD WHERE WRITER_NO = ? AND DEL_YN = 'N' ORDER BY ENROLL_DATE DESC";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Main.loginMember.getNo());

		ResultSet rs = pstmt.executeQuery();

		List<BoardVo> voList = new ArrayList<BoardVo>();
		BoardVo bVo = null;
		while (rs.next()) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String enrollDate = rs.getString("ENROLL_DATE");

			bVo = new BoardVo(no, title, null, null, enrollDate, null);
			voList.add(bVo);

		}
		if (bVo == null) {
			System.out.println("=====================");
			System.out.println("작성한 게시물 없음.");
			System.out.println("=====================");
		} else {

			TablePrinter.printTable(voList, new String[] { "no", "title", "enrollDate" },
					new String[] { "번호", "제목", "작성일자" });

		}

	}

}