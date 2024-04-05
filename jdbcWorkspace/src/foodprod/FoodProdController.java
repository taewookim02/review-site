package foodprod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import main.Main;
import util.JDBCTemplate;
import util.TablePrinter;

public class FoodProdController {
	
public void printMenu() throws Exception{
		
		System.out.println("----메뉴 선택----");
		System.out.println("1. 물품 조회 (최신순)");
		System.out.println("2. 물품 조회 (상품명)");
		System.out.println("3. 물품 넣기");
		System.out.print("번호 입력 : ");
		
		String num = Main.SC.nextLine();
		
		switch(num) {
		
		case "1": lookUpRecent(); break;
		case "2": lookUpName(); break;
		case "3": addFood(); break;
		default : System.out.println("잘못된 번호");
		
		}
		
	}
	
public void lookUpRecent() throws Exception{
	
	if(Main.loginMember == null) {
		System.out.println("로그인이 필요 합니다");
		return;
	}
	
	Connection conn = JDBCTemplate.getConn();
	
	String sql = "SELECT * FROM FOOD_PRODUCT ORDER BY FOOD_PROD_NO DESC";
	
	PreparedStatement pstmt =conn.prepareStatement(sql);
	
	ResultSet rs = pstmt.executeQuery();
	ArrayList<FoodProdVo> arr = new ArrayList<FoodProdVo>();
	boolean found = false;
	while(rs.next()) {
		found = true;
		String no = rs.getString("FOOD_PROD_NO");
		String name = rs.getString("NAME");
		String price = rs.getString("PRICE");
		String isdicount = rs.getString("IS_DISCOUNTINUED_YN");
		String description = rs.getString("DESCRIPTION");
		
		FoodProdVo fpv = new FoodProdVo(no, name, price, isdicount, description);
		arr.add(fpv);
	}
	
	if(found = false) {
		System.out.println("리뷰 조회 실패");
		return;
	}
	
	TablePrinter.printTable( arr , new String[] {"foodProdNo", "foodProdName", "price", "isDidcount", "description"}, 
			new String [] {"번호", "상품명", "가격", "단종 여부", "제품 설명"});
	
}

public void lookUpName() throws Exception{
    
	Connection conn = JDBCTemplate.getConn();
	
	String sql = "SELECT * FROM FOOD_PRODUCT WHERE NAME LIKE ?";
	
	System.out.print("검색할 상품명 : ");
	String inputName = Main.SC.nextLine();
	
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, "%" + inputName + "%");
	
	ResultSet rs = pstmt.executeQuery();
	ArrayList<FoodProdVo> arr = new ArrayList<FoodProdVo>();
	boolean found = false;
	
	while(rs.next()) {
		
		found = true;
		String no = rs.getString("FOOD_PROD_NO");
		String name = rs.getString("NAME");
		String price = rs.getString("PRICE");
		String isdicount = rs.getString("IS_DISCOUNTINUED_YN");
		String description = rs.getString("DESCRIPTION");
		
		FoodProdVo fpv = new FoodProdVo(no, name, price, isdicount, description);
		arr.add(fpv);
		
	}
	if(found = false) {
		System.out.println("리뷰 조회 실패");
		return;
	}
	TablePrinter.printTable( arr , new String[] {"foodProdNo", "foodProdName", "price", "isDidcount", "description"}, 
			new String [] {"번호", "상품명", "가격", "단종 여부", "제품 설명"});
	
	
	System.out.print("상품에 대해 리뷰를 작성하시겠습니까? Y/N ");
	String yon = Main.SC.nextLine();
	
	if(yon.equals("Y")) {
		if(Main.loginMember == null) {
			System.out.println("로그인이 필요 합니다");
			return;
		}
		
		Connection conn1 = JDBCTemplate.getConn();

		String sql1 = "INSERT INTO FOOD_REVIEW(FOOD_REVIEW_NO, REVIEW_TITLE, REVIEW, FOOD_PROD_NO, WRITER_NO, ENROLL_DATE) VALUES(SEQ_FOOD_REVIEW_NO.NEXTVAL,?,?,?,?,SYSDATE)";
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
	
	


public void addFood() throws Exception{
	
	Connection conn = JDBCTemplate.getConn();
	
	String sql = "INSERT INTO FOOD_PRODUCT(FOOD_PROD_NO, NAME, PRICE, IS_DISCOUNTINUED_YN, DESCRIPTION) VALUES(SEQ_FOOD_PROD_NO.NEXTVAL,?,?,?,?)";
	
	System.out.print("상품명 : ");
	String inputName = Main.SC.nextLine();
	System.out.print("가격 : ");
	String inputPrice = Main.SC.nextLine();
	System.out.print("단종 여부 (Y/N) : ");
	String inputIsDiscount = Main.SC.nextLine();
	System.out.print("제품 설명 : ");
	String inputDescription = Main.SC.nextLine();
	
	PreparedStatement pstmt = conn.prepareStatement(sql);
	
	pstmt.setString(1,inputName);
	pstmt.setString(2,inputPrice);
	pstmt.setString(3,inputIsDiscount);
	pstmt.setString(4,inputDescription);
	
	int result = pstmt.executeUpdate();
	
	if(result != 1) {
		System.out.println("상품 등록 실패하셨습니다.");
		return;
	}
	System.out.println("상품 등록 완료하셨습니다.");
} 



}
