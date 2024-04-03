package boosterReview;

import java.sql.Connection;
import java.sql.PreparedStatement;

import main.Main;
import util.JDBCTemplate;

public class BoosterReviewController {
	
	public void printMenu() {
		System.out.println("====BOOSTER REVIEW====");
		System.out.println("1. 구매후기 작성");
		System.out.println("2. 게시물 작성자명 조회");
		System.out.print("3. 게시물 삭제");
		System.out.print("4. 게시물 제목수정");
		System.out.print("5. 게시물 내용수정");
		String menu = Main.SC.nextLine();

		switch (menu) {
		case "1":
			write();
			break;
		case "2":
			selectWriter();
			break;
		case "3":
			deleteBoard();
		case "4":
			updateTitle();
		case "5":
			updateContent();
		default:
			break;
		}
	}
	
	
	public void write() {
		
		//validate
		if(Main.loginMember == null) {
			System.out.println("로그인 하고 오세요");
			return;
		}
		
		try {
		//conn
		Connection conn = JDBCTemplate.getConn();

		System.out.print("리뷰 내용 : ");
		String review = Main.SC.nextLine();
		System.out.print("내용 : ");
		String content = Main.SC.nextLine();
		
		//SQL
		String sql = "INSERT INTO BOOSTER_REVIEW (BOOSTER_REVIEW_NO, REVIEW, BOOSTER_PRO_NO, MEMBER_NO) VALUES (SEQ_BOOSTER_REVIEW_NO.NEXTVAL, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, review);
		pstmt.setString(2, 제품번호가져와야함);
		pstmt.setString(3, Main.loginMember.getNo());
		int result = pstmt.executeUpdate();
		
		//result
		if(result != 1) {
			System.out.println("게시글 작성 실패 ... ");
			return;
		}
		conn.commit();
		System.out.println("게시글 작성 성공 ! ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void selectWriter() {
		
	}

	public void deleteBoard() {
		
	}
	
	public void updateTitle() {
		
	}
	
	public void updateContent() {
		
	}
}
