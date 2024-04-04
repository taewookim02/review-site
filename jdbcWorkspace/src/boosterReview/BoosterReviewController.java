package boosterReview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import boosterproduct.BoosterProductVo;
import main.Main;
import member.MemberVo;
import util.JDBCTemplate;

public class BoosterReviewController {
	
	public void printMenu() {
		System.out.println("====BOOSTER REVIEW====");
		System.out.println("1. 구매후기 작성");
		System.out.println("2. 게시물 작성자명 조회");
		System.out.println("3. 게시물 삭제");
		System.out.println("4. 게시물 제목수정");
		System.out.println("5. 게시물 내용수정");
		System.out.println("6. 리뷰 내용확인");
		
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
			break;
		case "4":
			updateTitle();
			break;
		case "5":
			updateContent();
			break;
		default:
			break;
		}
	}
	
	
	public void write() {
		
		if(Main.loginMember == null) {
			System.out.println("로그인 하고 오세요");
			return;
		}
		
		try {
		Connection conn = JDBCTemplate.getConn();

		System.out.println("리뷰할 제품 번호 : ");
		String no = Main.SC.nextLine();
		System.out.println("리뷰 제목 : ");
		String reviewTitle = Main.SC.nextLine();
		System.out.println("리뷰 내용 : ");
		String review = Main.SC.nextLine();

		String sql = "INSERT INTO BOOSTER_REVIEW (BOOSTER_REVIEW_NO, REVIEW_TITLE, REVIEW, BOOSTER_PROD_NO, MEMBER_NO) VALUES (SEQ_BOOSTER_REVIEW_NO.NEXTVAL, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, reviewTitle);
		pstmt.setString(2, review);
		pstmt.setString(3, no);
		pstmt.setString(4, Main.loginMember.getNo());
		int result = pstmt.executeUpdate();
		
		if(result != 1) {
			System.out.println("게시글 작성 실패 ... ");
			return;
		}
		System.out.println("게시글 작성 성공 ! ");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void selectWriter() {
		
		try {
		Connection conn = JDBCTemplate.getConn();

		System.out.println("리뷰 번호 : ");
		String no = Main.SC.nextLine();
		
		BoosterProductVo bpvo = new BoosterProductVo();
		
		String sql = "SELECT NICK FROM MEMBER M JOIN BOOSTER_REVIEW R ON M.NO = R.MEMBER_NO WHERE BOOSTER_REVIEW_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		
		ResultSet rs = pstmt.executeQuery();
		
		MemberVo vo = null;
		
		if( rs.next() ) {
		String nick = rs.getString("NICK");
		
		vo = new MemberVo();
		vo.setNick(nick);

		
		System.out.println(vo.getNick());
		
		} else {
			System.out.println("리뷰 작성 실패!");
		}
		
		} catch (Exception e) {
		e.printStackTrace();
	}
}

	public void deleteBoard() {
		
		try {
		Connection conn = JDBCTemplate.getConn();

		if(Main.loginMember.getAdmin_yn().equals("Y")) {
		System.out.println("삭제할 게시글 번호 : ");
		String no = Main.SC.nextLine();

		String sql = "DELETE FROM BOOSTER_REVIEW WHERE BOOSTER_REVIEW_NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		
		if(result != 1) {
			System.out.println("게시글 삭제  실패 ... ");
			return;
		}
		System.out.println("게시글 삭제 성공 ! ");
			
		} else if (Main.loginMember.getAdmin_yn().equals("N")) {
		System.out.println("삭제할 게시글 번호 : ");
		String no = Main.SC.nextLine();

		String sql = "UPDATE BOOSTER_REVIEW SET QUIT_YN = 'Y' WHERE NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		
		if(result != 1) {
			System.out.println("게시글 삭제  실패 ... ");
			return;
		}
		System.out.println("게시글 삭제 성공 ! ");
		} else {
			System.out.println("로그인 하고 오세요");
			return;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateTitle() {
		
		try {
			Connection conn = JDBCTemplate.getConn();
		
			if (Main.loginMember != null) {
				
				System.out.println("수정할 제목 : ");
				String reviewTitle = Main.SC.nextLine();
				System.out.println("제목 수정할 게시글 번호 : ");
				String no = Main.SC.nextLine();
		
				String sql = "UPDATE BOOSTER_REVIEW SET REVIEW_TITLE = ? WHERE BOOSTER_REVIEW_NO = ?";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, reviewTitle);
				pstmt.setString(2, no);
				int result = pstmt.executeUpdate();
				
				if(result != 1) {
					System.out.println("제목 수정 실패 ... ");
					return;
				}
				System.out.println("제목 수정 성공 ! ");
				} else {
					System.out.println("로그인 하고 오세요");
					return;
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
		
		
	}
	
	public void updateContent() {
		
		try {
			Connection conn = JDBCTemplate.getConn();
		
			if (Main.loginMember != null) {
				
				System.out.println("수정할 내용 : ");
				String review = Main.SC.nextLine();
				System.out.println("제목 수정할 게시글 번호 : ");
				String no = Main.SC.nextLine();
		
				String sql = "UPDATE BOOSTER_REVIEW SET REVIEW = ? WHERE BOOSTER_REVIEW_NO = ?";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, review);
				pstmt.setString(2, no);
				int result = pstmt.executeUpdate();
				
				if(result != 1) {
					System.out.println("내용 수정 실패 ... ");
					return;
				}
				System.out.println("내용 수정 성공 ! ");
				} else {
					System.out.println("로그인 하고 오세요");
					return;
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
		
		
	}
}
