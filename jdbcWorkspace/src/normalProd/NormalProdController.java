package normalProd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import main.Main;
import util.JDBCTemplate;
import util.TablePrinter;

public class NormalProdController {

	public void printMenu() throws Exception {

		System.out.println("----메뉴 선택----");
		System.out.println("1. 물품 조회 (최신순)");
		System.out.println("2. 물품 조회 (상품명)");
		System.out.println("3. 물품 넣기");
		System.out.print("번호 입력 : ");
		String num = Main.SC.nextLine();

		switch (num) {

		case "1":
			lookUpRecent();
			break;
		case "2":
			lookUpName();
			break;
		case "3":
			addNormal();
			break;
		default:
			System.out.println("잘못된 번호");
			return;
		}

	}

	private void lookUpRecent() throws Exception {
		
		
		Connection conn = JDBCTemplate.getConn();
		
		String sql = "SELECT * FROM NORMAL_PRODUCT ORDER BY NORMAL_PROD_NO DESC";
		
		PreparedStatement pstmt =conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		ArrayList<NormalProdVo> arr = new ArrayList<NormalProdVo>();
		boolean found = false;
		while(rs.next()) {
			found = true;
			String no = rs.getString("NORMAL_PROD_NO");
			String name = rs.getString("NAME");
			String price = rs.getString("PRICE");
			String isdicount = rs.getString("IS_DISCOUNTINUED_YN");
			String description = rs.getString("DESCRIPTION");
			
			NormalProdVo npv = new NormalProdVo(no, name, price, isdicount, description);
			arr.add(npv);
		}
		
		if(found = false) {
			System.out.println("상품 조회 실패");
			return;
		}
		
		TablePrinter.printRecordsVertically( arr , new String[] {"normaProdlNo", "normalProdName", "price", "isDiscount", "description"}, 
				new String [] {"번호", "상품명", "가격", "단종 여부", "제품 설명"});
		
		
	}

	public void lookUpName() throws Exception {
		Connection conn = JDBCTemplate.getConn();

		System.out.print("상품명을 입력해주세요 : ");
		String name = Main.SC.nextLine();

		String sql = "SELECT * FROM NORMAL_PRODUCT WHERE NAME LIKE ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "%" + name + "%");

		ResultSet rs = pstmt.executeQuery();

		ArrayList<NormalProdVo> volist = new ArrayList<NormalProdVo>();
		boolean found = false;

		while (rs.next()) {

			found = true;
			String no = rs.getString("NORMAL_PROD_NO");
			String inputname = rs.getString("NAME");
			String price = rs.getString("PRICE");
			String yn = rs.getNString("IS_DISCOUNTINUED_YN");
			String description = rs.getString("DESCRIPTION");

			NormalProdVo vo = new NormalProdVo(no, inputname, price, yn, description);
			volist.add(vo);

		}
		if (found = false) {
			System.out.println("상품 조회 실패");
			return;
		}
		TablePrinter.printRecordsVertically( volist , new String[] {"normaProdlNo", "normalProdName", "price", "isDiscount", "description"}, 
				new String [] {"번호", "상품명", "가격", "단종 여부", "제품 설명"});
		
		System.out.print("상품에 대해 리뷰를 작성하시겠습니까? Y/N");
		String yon = Main.SC.nextLine();
		
		if(yon.equals("Y")) {
			if(Main.loginMember == null) {
				System.out.println("로그인이 필요 합니다");
				return;
			}
			
			Connection conn1 = JDBCTemplate.getConn();

			String sql1 = "INSERT INTO NORMAL_REVIEW(NORMAL_REVIEW_NO, REVIEW_TITLE, REVIEW, NORMAL_PROD_NO, WRITER_NO, ENROLL_DATE) VALUES(SEQ_FOOD_REVIEW_NO.NEXTVAL,?,?,?,?,SYSDATE)";
			System.out.println("리뷰 제목 : ");
			String title = Main.SC.nextLine();
			System.out.println("리뷰 내용 : ");
			String content = Main.SC.nextLine();
			System.out.println("재품 번호 (oniy number) : ");
			String prodName = Main.SC.nextLine();
			

			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, title);
			pstmt1.setString(2, content);
			pstmt1.setString(3, prodName);
			pstmt1.setString(4, Main.loginMember.getNo());

			int result = pstmt1.executeUpdate();

			if (result != 1) {
				System.out.println("리뷰 작성 실패");
				return;
			}
			System.out.println("리뷰 등록 완료");

		} else if(yon.equals("N")) {
			return;
		} 

	}

	private void addNormal() throws Exception {
//		
		if(Main.loginMember == null) {
			System.out.println("로그인이 필요 합니다");
			return;
		}
//		
		Connection conn = JDBCTemplate.getConn();

		System.out.print("상품명 : ");
		String name = Main.SC.nextLine();
		System.out.print("가격 : ");
		String price = Main.SC.nextLine();
		System.out.print("단종 여부 (Y/N) : ");
		String inputIsDiscount = Main.SC.nextLine();
		System.out.print("상세설명 : ");
		String description = Main.SC.nextLine();

		String sql = "INSERT INTO NORMAL_PRODUCT(NORMAL_PROD_NO , NAME , PRICE ,IS_DISCOUNTINUED_YN,DESCRIPTION) \r\n"
				+ "VALUES ( SEQ_NORMAL_PROD_NO.NEXTVAL , ? ,? ,?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, price);
		pstmt.setString(3, inputIsDiscount);
		pstmt.setString(4, description);

		int result = pstmt.executeUpdate();

		if (result != 1) {
			System.out.println("상품 등록 실패하셨습니다");
			return;
		}

		System.out.println("상품 등록 성공하셨습니다");

	}
}
