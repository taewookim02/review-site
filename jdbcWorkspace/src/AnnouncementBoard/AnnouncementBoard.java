package AnnouncementBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import board.BoardVo;
import main.Main;
import util.JDBCTemplate;

public class AnnouncementBoard {

	public void printMenu() throws Exception {

		System.out.println("1. 공지사항 등록 (관리자)");
		System.out.println("2. 공지사항 삭제 (관리자)");
		System.out.println("3. 공지사항 제목 수정 (관리자)");
		System.out.println("4. 공지사항 내용 수정 (관리자)");
		System.out.println("5. 공지사항 전체 조회");
		System.out.println("6. 공지사항 번호로 상세 조회");
		System.out.println("7. 공지사항 생성일자로 조회");
		System.out.println("8. 이전으로 돌아가기");

		System.out.print("메뉴번호: ");
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
			System.err.println("관리자 계정으로 로그인 바랍니다.");
			return;
		}

		if (Main.loginMember.getNo().equals("1")) {

			Connection conn = JDBCTemplate.getConn();

			String sql = "INSERT INTO ANNOUNCEMENT_BOARD(NO, TITLE, CONTENT, WRITER_NO) VALUES(SEQ_BOARD_NO.NEXTVAL , ? , ?, '1')";

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
				System.out.println("게시글 등록에 실패하셨습니다.");
				System.out.println("====================");
				return;
			}
			System.out.println("====================");
			System.out.println("공지사항 등록 완료");
			System.out.println("====================");

		} else {
			System.out.println("====================");
			System.out.println("일반 회원은 권한이 없습니다.");
			System.out.println("====================");
			return;
		}

	}

	private void delete() throws Exception {

		if (Main.loginMember.getNo().equals("1")) {

			Connection conn = JDBCTemplate.getConn();

			String sql = "UPDATE ANNOUNCEMENT_BOARD SET DEL_YN = 'Y' WHERE NO = ?";
			System.out.print("삭제할 게시물 번호 : ");
			String no = Main.SC.nextLine();

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);

			int result = pstmt.executeUpdate();

			if (result != 1) {
				System.out.println("====================");
				System.out.println("공지사항 삭제에 실패했습니다.");
				System.out.println("====================");
				return;
			}
			System.out.println("====================");
			System.out.println("공지사항이 삭제되었습니다.");
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
			System.out.print("변경할 게시물 번호 선택 : ");
			String no = Main.SC.nextLine();
			System.out.print("변경할 제목 : ");
			String title = Main.SC.nextLine();

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, no);

			int result = pstmt.executeUpdate();

			if (result != 1) {
				System.out.println("====================");
				System.out.println("제목 수정에 실패하였습니다.");
				System.out.println("====================");
				return;
			}
			System.out.println("====================");
			System.out.println("제목 수정이 완료되었습니다.");
			System.out.println("====================");

		}

	}

	private void editContent() throws Exception {

		if (Main.loginMember.getAdmin_yn().equals("Y")) {

			Connection conn = JDBCTemplate.getConn();

			String sql = "UPDATE ANNOUNCEMENT_BOARD SET CONTENT = ? WHERE NO = ? AND DEL_YN = 'N'";
			System.out.print("변경할 게시물 번호 선택 : ");
			String no = Main.SC.nextLine();
			System.out.print("변경할 내용 : ");
			String content = Main.SC.nextLine();

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setString(2, no);

			int result = pstmt.executeUpdate();

			if (result != 1) {
				System.out.println("====================");
				System.out.println("내용 수정에 실패하였습니다.");
				System.out.println("====================");
				return;
			}
			System.out.println("====================");
			System.out.println("내용 수정이 완료되었습니다.");
			System.out.println("====================");

		}

	}

	private void selectAllAnnouncement() throws Exception {

		
		
	}

	private void selectAnnouncementNo() {

	}

	private void selectAnnouncementEnrollDate() {

	}

}
