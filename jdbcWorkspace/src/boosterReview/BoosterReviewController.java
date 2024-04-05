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
		System.out.println("2. 리뷰 조회");
		System.out.println("3. 리뷰 삭제");
		System.out.println("4. 리뷰 제목수정");
		System.out.println("5. 리뷰 내용수정");
		System.out.println("6. 리뷰 전체 목록 조회");
		System.out.print("메뉴 번호 : ");
		String menu = Main.SC.nextLine();

		switch (menu) {
		case "1":
			write();
			break;
		case "2":
			selectReview();
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
		case "6":
			selectReviewList();
		default:
			break;
		}
	}

	public void write() {

		if (Main.loginMember == null) {
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

			if (result != 1) {
				System.out.println("게시글 작성 실패 ... ");
				return;
			}
			System.out.println("게시글 작성 성공 ! ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectReview() {
		try {
			Connection conn = JDBCTemplate.getConn();

			if (Main.loginMember.getAdmin_yn().equals("Y")) {

				System.out.println(Main.loginMember.getNick() + " 님 환영합니다!");

				System.out.print("리뷰 번호 : ");
				String no = Main.SC.nextLine();

				String sql = "SELECT BOOSTER_REVIEW_NO, REVIEW_TITLE, REVIEW, BOOSTER_PROD_NO, M.NICK, ENROLL_DATE FROM BOOSTER_REVIEW R JOIN MEMBER M ON M.NO = R.MEMBER_NO WHERE BOOSTER_REVIEW_NO = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, no);

				ResultSet rs = pstmt.executeQuery();

				BoosterReviewVo vo = null;
				MemberVo vo3 = null;

				if (rs.next()) {
					String boosterReviewNo = rs.getString("BOOSTER_REVIEW_NO");
					String reviewTitle = rs.getString("REVIEW_TITLE");
					String review = rs.getString("REVIEW");
					String boosterProdNo = rs.getString("BOOSTER_PROD_NO");
					String nick = rs.getString("NICK");
					String enrollDate = rs.getString("ENROLL_DATE");

					vo = new BoosterReviewVo();
					vo3 = new MemberVo();
					vo.setBoosterReviewNo(boosterReviewNo);
					vo.setReviewTitle(reviewTitle);
					vo.setReview(review);
					vo.setBoosterProdNo(boosterProdNo);
					vo3.setNick(nick);
					vo.setEnrollDate(enrollDate);

					System.out.println(vo.getBoosterReviewNo());
					System.out.println(vo.getReviewTitle());
					System.out.println(vo.getReview());
					System.out.println(vo.getBoosterProdNo());
					System.out.println(vo3.getNick());
					System.out.println(vo.getEnrollDate());
				}
			} else {
				if (Main.loginMember == null) {
					System.out.println("로그인하고 오세요.");
					return;
				}
				System.out.println(Main.loginMember.getNick() + " 님 환영합니다!");

				System.out.print("리뷰 번호 : ");
				String no2 = Main.SC.nextLine();

				String sql2 = "SELECT R.BOOSTER_REVIEW_NO, R.REVIEW_TITLE, R.REVIEW, M.NICK , R.ENROLL_DATE, R.QUIT_YN FROM BOOSTER_REVIEW R JOIN MEMBER M ON M.NO = R.MEMBER_NO WHERE BOOSTER_REVIEW_NO = ? AND R.QUIT_YN = 'N'";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, no2);
//				pstmt2.setString(2, Main.loginMember.getNo());

				ResultSet rs2 = pstmt2.executeQuery();

				if (rs2.next()) {

					String boosterReviewNo = rs2.getString("BOOSTER_REVIEW_NO");
					String reviewTitle = rs2.getString("REVIEW_TITLE");
					String review = rs2.getString("REVIEW");
					String nick = rs2.getString("NICK");
					String enrollDate = rs2.getString("ENROLL_DATE");
					String quitYn = rs2.getString("QUIT_YN");

					BoosterReviewVo vo2 = new BoosterReviewVo();
					MemberVo vo4 = new MemberVo();
					vo2.setBoosterReviewNo(boosterReviewNo);
					vo2.setReviewTitle(reviewTitle);
					vo2.setReview(review);
					vo4.setNick(nick);
					vo2.setEnrollDate(enrollDate);
					vo2.setQuitYn(quitYn);
					
					if(vo2.getQuitYn().equals("Y")) {
						System.out.println("삭제된 리뷰입니다.");
					}

					System.out.println(vo2.getBoosterReviewNo());
					System.out.println(vo2.getReviewTitle());
					System.out.println(vo2.getReview());
					System.out.println(vo4.getNick());
					System.out.println(vo2.getEnrollDate());
					
//					if(vo4.getNick() == Main.loginMember.getNick()) {
//						System.out.println("리뷰남길지말지여부나중에하기");
//						return;
//					}
				}
			}
		} // try
		catch (Exception e) {
			e.printStackTrace();
		}
	} // method

	public void deleteBoard() {

		try {
			Connection conn = JDBCTemplate.getConn();

			if (Main.loginMember.getAdmin_yn().equals("Y")) {
				System.out.print("삭제할 리뷰 번호 : ");
				String no = Main.SC.nextLine();

				String sql = "DELETE FROM BOOSTER_REVIEW WHERE BOOSTER_REVIEW_NO = ?";

				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, no);
				int result = pstmt.executeUpdate();

				if (result != 1) {
					System.out.println("리뷰 삭제  실패 ... ");
					return;
				}
				System.out.println("리뷰 삭제 성공 ! ");

			} else if (Main.loginMember.getAdmin_yn().equals("N")) {
				System.out.print("삭제할 리뷰 번호 : ");
				String no = Main.SC.nextLine();

				String sql = "UPDATE BOOSTER_REVIEW SET QUIT_YN = 'Y' WHERE BOOSTER_REVIEW_NO = ? AND MEMBER_NO = ?";

				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, no);
				pstmt.setString(2, Main.loginMember.getNo());
				
				int result = pstmt.executeUpdate();

				if (result != 1) {
					System.out.println("리뷰 삭제  실패 ... ");
					return;
				}
				System.out.println("리뷰 삭제 성공 ! ");
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

			if (Main.loginMember.getAdmin_yn().equals("Y")) {
				System.out.print("제목 수정할 리뷰 번호 : ");
				String no = Main.SC.nextLine();
				System.out.print("수정할 제목 : ");
				String title = Main.SC.nextLine();

				String sql = "UPDATE BOOSTER_REVIEW SET REVIEW_TITLE = ? WHERE BOOSTER_REVIEW_NO = ?";

				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, no);
				int result = pstmt.executeUpdate();

				if (result != 1) {
					System.out.println("제목 수정 실패 ... ");
					return;
				}
				System.out.println("제목 수정 성공 ! ");

			} else if (Main.loginMember.getAdmin_yn().equals("N")) {
				System.out.print("제목 수정할 리뷰 번호 : ");
				String no = Main.SC.nextLine();
				System.out.print("수정할 제목 : ");
				String title = Main.SC.nextLine();

				String sql = "UPDATE BOOSTER_REVIEW SET REVIEW_TITLE = ? WHERE BOOSTER_REVIEW_NO = ? AND MEMBER_NO = ?";

				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, no);
				pstmt.setString(3, Main.loginMember.getNo());
				
				int result = pstmt.executeUpdate();

				if (result != 1) {
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

			if (Main.loginMember.getAdmin_yn().equals("Y")) {
				System.out.print("내용 수정할 리뷰 번호 : ");
				String no = Main.SC.nextLine();
				System.out.print("수정할 내용 : ");
				String content = Main.SC.nextLine();

				String sql = "UPDATE BOOSTER_REVIEW SET REVIEW = ? WHERE BOOSTER_REVIEW_NO = ?";

				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, content);
				pstmt.setString(2, no);
				int result = pstmt.executeUpdate();

				if (result != 1) {
					System.out.println("내용 수정 실패 ... ");
					return;
				}
				System.out.println("내용 수정 성공 ! ");

			} else if (Main.loginMember.getAdmin_yn().equals("N")) {
				System.out.print("내용 수정할 리뷰 번호 : ");
				String no = Main.SC.nextLine();
				System.out.print("수정할 내용 : ");
				String content = Main.SC.nextLine();

				String sql = "UPDATE BOOSTER_REVIEW SET REVIEW = ? WHERE BOOSTER_REVIEW_NO = ? AND MEMBER_NO = ?";

				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, content);
				pstmt.setString(2, no);
				pstmt.setString(3, Main.loginMember.getNo());
				
				int result = pstmt.executeUpdate();

				if (result != 1) {
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

	public void selectReviewList() {

		try {
			Connection conn = JDBCTemplate.getConn();

			String sql = "SELECT BOOSTER_REVIEW_NO, REVIEW_TITLE, M.NICK, ENROLL_DATE FROM BOOSTER_REVIEW R JOIN MEMBER M ON R.MEMBER_NO = M.NO";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			BoosterReviewVo vo = null;
			MemberVo vo3 = null;

			while (rs.next()) {
				String boosterReviewNo = rs.getString("BOOSTER_REVIEW_NO");
				String reviewTitle = rs.getString("REVIEW_TITLE");
				String nick = rs.getString("NICK");
				String enrollDate = rs.getString("ENROLL_DATE");

				vo = new BoosterReviewVo();
				vo3 = new MemberVo();
				vo.setBoosterReviewNo(boosterReviewNo);
				vo.setReviewTitle(reviewTitle);
				vo3.setNick(nick);
				vo.setEnrollDate(enrollDate);

				System.out.println(vo.getBoosterReviewNo());
				System.out.println(vo.getReviewTitle());
				System.out.println(vo3.getNick());
				System.out.println(vo.getEnrollDate());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
