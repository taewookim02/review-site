package normalReview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import foodreview.FoodReviewVo;
import main.Main;
import util.JDBCTemplate;

public class NormalReviewController {

	public void printMenu()throws Exception {
		
		System.out.println("----메뉴 선택----");
		System.out.println("1. 리뷰 작성");
		System.out.println("2. 리뷰 삭제");
		System.out.println("3. 리뷰 제목 수정");
		System.out.println("4. 리뷰 내용 수정");
		System.out.println("5. 리뷰 목록 조회");
		System.out.println("6. 리뷰 상세 조회 (작성자명)");
		System.out.print("번호 입력 : ");

		String num = Main.SC.nextLine();
		
		switch (num) {

		case "1": write(); break;
		case "2": delete(); break;
		case "3": editTitle(); break;
		case "4": editContent(); break;
		case "5": lookUp(); break;
		case "6": searchWriter(); break;
		default: System.out.println("잘못된 번호");
		}
	
	}
	
	public void write() throws Exception {
		if (Main.loginMember == null) {
			System.out.println("로그인 필요");
			return;
		}

		Connection conn = JDBCTemplate.getConn();

		String sql = "INSERT INTO NORMAL_REVIEW(NORMAL_REVIEW_NO, REVIEW_TITLE, REVIEW, NORMAL_PROD_NO, WRITER_NO, ENROLL_DATE) VALUES(SEQ_NORMAL_REVIEW_NO.NEXTVAL,?,?,?,?,SYSDATE)";
		System.out.println("리뷰 제목 : ");
		String title = Main.SC.nextLine();
		System.out.println("리뷰 내용 : ");
		String content = Main.SC.nextLine();
		System.out.println("재품 번호 (oniy number) : ");
		String prodName = Main.SC.nextLine();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, prodName);
		pstmt.setString(4, Main.loginMember.getNo());

		int result = pstmt.executeUpdate();

		if (result != 1) {
			System.out.println("리뷰 작성 실패");
			return;
		}
		System.out.println("리뷰 등록 완료");

	}

	public void delete() throws Exception {

		if (Main.loginMember == null) {
			System.out.println("로그인 필요");
			return;
		}

		if (!Main.loginMember.getAdmin_yn().equals("Y")) {

			Connection conn = JDBCTemplate.getConn();

			String sql = "UPDATE NORMAL_REVIEW SET QUIT_YN = 'Y' WHERE NORMAL_REVIEW_NO = ? AND WRITER_NO = ?";
			System.out.println("삭제할 게시물 번호 : ");
			String num = Main.SC.nextLine();

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.setString(2, Main.loginMember.getNo());

			int result = pstmt.executeUpdate();

			if (result != 1) {
				System.out.println("리뷰 삭제 실패");
				return;
			}
			System.out.println("리뷰 삭제 완료");
		} else {
			System.out.println("관리자 전용");
			Connection conn = JDBCTemplate.getConn();

			String sql = "DELETE NORMAL_REVIEW WHERE NORMAL_REVIEW_NO =?";
			System.out.println("삭제할 게시물 번호 : ");
			String num = Main.SC.nextLine();

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);

			int result = pstmt.executeUpdate();

			if (result != 1) {
				System.out.println("리뷰 삭제 실패");
				return;
			}
			System.out.println("리뷰 삭제 완료");
		}
	}

	public void editTitle() throws Exception {
		if (Main.loginMember == null) {
			System.out.println("로그인 필요");
			return;
		}
//		System.out.println(Main.loginMember.getNo()); // 2
		Connection conn = JDBCTemplate.getConn();

		String sql = "UPDATE NORMAL_REVIEW SET REVIEW_TITLE = ? WHERE NORMAL_REVIEW_NO = ? AND WRITER_NO = ?";

		System.out.println("수정 할 리뷰 번호 : ");
		String num = Main.SC.nextLine();
		System.out.println("수정 할 리뷰 제목 : ");
		String title = Main.SC.nextLine();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title); // good
		pstmt.setString(2, num); // 
		pstmt.setString(3, Main.loginMember.getNo());
		
		int result = pstmt.executeUpdate();
		
		if (result != 1) {
			System.out.println("리뷰 제목 수정 실패"); // 여기서 
			return;
		}
		System.out.println("리뷰 제목 수정 완료");

	}

	public void editContent() throws Exception {
		
		if (Main.loginMember == null) {
			System.out.println("로그인 필요");
			return;
		}
		Connection conn = JDBCTemplate.getConn();

		String sql = "UPDATE NORMAL_REVIEW SET REVIEW = ? WHERE NORMAL_REVIEW_NO = ? AND WRITER_NO = ?";

		System.out.println("수정 할 리뷰 번호 : ");
		String num = Main.SC.nextLine();
		System.out.println("수정 할 리뷰 내용 : ");
		String content = Main.SC.nextLine();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, content); // good
		pstmt.setString(2, num); // 
		pstmt.setString(3, Main.loginMember.getNo());
		
		int result = pstmt.executeUpdate();
		
		if (result != 1) {
			System.out.println("리뷰 제목 수정 실패"); 
			return;
		}
		System.out.println("리뷰 제목 수정 완료");

	}
		

	public void lookUp() throws Exception {
		
		if(Main.loginMember != null && Main.loginMember.getAdmin_yn().equals("Y")) {
			Connection conn1 = JDBCTemplate.getConn();
			
			String sql1= "SELECT R.NORMAL_REVIEW_NO, R.REVIEW_TITLE, M.NICK ,TO_CHAR(R.ENROLL_DATE, 'YYYY-MM-DD HH:MI:SS') AS ENROLL_DATE FROM NORMAL_REVIEW R JOIN MEMBER M ON R.WRITER_NO = M.NO"; 
			
			PreparedStatement pstmt1 = conn1.prepareStatement(sql1);
			
			ResultSet rs1 = pstmt1.executeQuery();
			
			ArrayList<NormalReviewVo> arr1 = new ArrayList<NormalReviewVo>();
			boolean found1 = false;
			while(rs1.next()) {
				found1 = true;
				String no = rs1.getString("NORMAL_REVIEW_NO");
				String title = rs1.getString("REVIEW_TITLE");
				String nick = rs1.getString("NICK");
				String enrolldate = rs1.getString("ENROLL_DATE");
				
				NormalReviewVo nrv1 = new NormalReviewVo(no, title, null, null, nick, enrolldate, null);
				arr1.add(nrv1);
			}
			
			if(found1 = false) {
				System.out.println("리뷰 조회 실패");
				return;
			}
			
			System.out.printf("%-5s | %-15s | %-5s | %-20s%n", "번호", "리뷰 제목", "작성자", "작성 날짜");
			
			
			for(NormalReviewVo nrv1 : arr1) {
				System.out.printf("%-6s | %-17s | %-5s | %-19s%n", nrv1.getNormalReviewlNo(), nrv1.getNormalReviewTilte(), nrv1.getWriterNo(), nrv1.getEnrollDate());
				
			}
		}else {
		Connection conn = JDBCTemplate.getConn();
		
		String sql = "SELECT R.NORMAL_REVIEW_NO, R.REVIEW_TITLE, M.NICK ,TO_CHAR(R.ENROLL_DATE, 'YYYY-MM-DD HH:MI:SS') AS ENROLL_DATE FROM NORMAL_REVIEW R JOIN MEMBER M ON R.WRITER_NO = M.NO WHERE R.QUIT_YN = 'N'"; 
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
			ArrayList<NormalReviewVo> arr = new ArrayList<NormalReviewVo>();
			boolean found = false;
			while(rs.next()) {
				found = true;
				String no = rs.getString("NORMAL_REVIEW_NO");
				String title = rs.getString("REVIEW_TITLE");
				String nick = rs.getString("NICK");
				String enrolldate = rs.getString("ENROLL_DATE");
				
				NormalReviewVo nrv = new NormalReviewVo(no, title, null, null, nick, enrolldate, null);
				arr.add(nrv);
			}
			
			if(found = false) {
				System.out.println("리뷰 조회 실패");
				return;
			}
			
			 System.out.printf("%-5s | %-15s | %-5s | %-20s%n", "번호", "리뷰 제목", "작성자", "작성 날짜");


			for(NormalReviewVo nrv : arr) {
				 System.out.printf("%-6s | %-17s | %-5s | %-19s%n", nrv.getNormalReviewlNo(), nrv.getNormalReviewTilte(), nrv.getWriterNo(), nrv.getEnrollDate());

			}
				
			}  
		
		
	}

	public void searchWriter() throws Exception {
		
		if(Main.loginMember != null && Main.loginMember.getAdmin_yn().equals("Y")) {
			
			Connection conn1 = JDBCTemplate.getConn();
			
			String sql1 = "SELECT R.NORMAL_REVIEW_NO, R.REVIEW_TITLE, R.REVIEW,M.NICK ,TO_CHAR(R.ENROLL_DATE, 'YYYY-MM-DD HH:MI:SS') AS ENROLL_DATE FROM NORMAL_REVIEW R JOIN MEMBER M ON R.WRITER_NO = M.NO WHERE M.NICK = ?";
			System.out.println("찾을 작성자 닉네임 : ");
			String name1 = Main.SC.nextLine();
			
			PreparedStatement pstmt1 = conn1.prepareStatement(sql1);
			pstmt1.setString(1, name1);
			
			ResultSet rs1 = pstmt1.executeQuery();
			
			ArrayList<NormalReviewVo> arr1 = new ArrayList<NormalReviewVo>();
			boolean found1 = false;
			while(rs1.next()) {
				found1 = true;
				String no = rs1.getString("NORMAL_REVIEW_NO");
				String title = rs1.getString("REVIEW_TITLE");
				String content = rs1.getString("REVIEW");
				String nick = rs1.getString("NICK");
				String enrolldate = rs1.getString("ENROLL_DATE");
				
				NormalReviewVo nrv1 = new NormalReviewVo(no, title, content, null, nick, enrolldate, null);
				arr1.add(nrv1);
			}
			
			if(found1 = false) {
				System.out.println("리뷰 조회 실패");
				return;
			}
			
			System.out.printf("%-5s | %-15s | %-20s | %-5s | %-20s%n", "번호", "리뷰 제목","리뷰 내용" ,"작성자", "작성 날짜");
			
			
			for(NormalReviewVo nrv1 : arr1) {
				System.out.printf("%-6s | %-17s | %-23s| %-5s | %-19s%n", nrv1.getNormalReviewlNo(), nrv1.getNormalReviewTilte(),nrv1.getNormalReview() ,nrv1.getWriterNo(), nrv1.getEnrollDate());
				
			}
		}else {
		Connection conn = JDBCTemplate.getConn();
		
		String sql = "SELECT R.NORMAL_REVIEW_NO, R.REVIEW_TITLE, R.REVIEW ,M.NICK ,TO_CHAR(R.ENROLL_DATE, 'YYYY-MM-DD HH:MI:SS') AS ENROLL_DATE FROM NORMAL_REVIEW R JOIN MEMBER M ON R.WRITER_NO = M.NO WHERE R.QUIT_YN = 'N'AND M.NICK = ?";
		System.out.println("찾을 작성자 닉네임 : ");
		String name = Main.SC.nextLine();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<NormalReviewVo> arr = new ArrayList<NormalReviewVo>();
		boolean found = false;
		while(rs.next()) {
			found = true;
			String no = rs.getString("NORMAL_REVIEW_NO");
			String title = rs.getString("REVIEW_TITLE");
			String content = rs.getString("REVIEW");
			String nick = rs.getString("NICK");
			String enrolldate = rs.getString("ENROLL_DATE");
			
			NormalReviewVo nrv = new NormalReviewVo(no, title, content, null, nick, enrolldate, null);
			arr.add(nrv);
		}
		
		if(found = false) {
			System.out.println("리뷰 조회 실패");
			return;
		}
		
		 System.out.printf("%-5s | %-15s | %-20s | %-5s | %-20s%n", "번호", "리뷰 제목", "리뷰 내용","작성자", "작성 날짜");


		for(NormalReviewVo nrv : arr) {
			 System.out.printf("%-6s | %-17s | %-23s | %-5s | %-19s%n", nrv.getNormalReviewlNo(), nrv.getNormalReviewTilte(),nrv. getNormalReview(),nrv.getWriterNo(), nrv.getEnrollDate());

		}
	}
	}
	
	

}
