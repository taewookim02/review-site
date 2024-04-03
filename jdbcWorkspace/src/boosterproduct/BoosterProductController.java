package boosterproduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.Main;
import member.MemberVo;
import util.JDBCTemplate;

public class BoosterProductController {

	public void printMenu() {
		System.out.println("====BOOSTER PRODUCT====");
		System.out.println("1. 물품 조회 (최신순)");
		System.out.println("2. 물품 조회 (상품명)");
		System.out.print("3. 물품 넣기");
	  	System.out.print("번호 입력 : ");
		String menu = Main.SC.nextLine();

		switch (menu) {
		case "1":
			selectList();
			break;
		case "2":
			selectByName();
			break;
		case "3":
			write();
		default:
			break;
		}
	}
	
	public void selectList() {
		
		try {
		Connection conn = JDBCTemplate.getConn();
		String sql = "SELECT * FROM BOOSTER_PRODUCT";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
		String boosterProdNo = rs.getString("BOOSTER_PROD_NO");
		String name = rs.getString("NAME");
		String price = rs.getString("PRICE");
		String isDiscountinuedYn = rs.getString("IS_DISCOUNTINUED_YN");
		String description = rs.getString("DESCRIPTION");
		
		System.out.printf("%-5s|%-15s|%-10s|%-5s|%-20s%n", "번호", "제품 이름", "가격", "단종 여부", "제품 설명");
		BoosterProductVo vo = new BoosterProductVo(boosterProdNo, name, price, isDiscountinuedYn, description);
		for(BoosterProductVo bpv : arr) {
			System.out.printf("%-6s|%-15s|%-10s|%-8s|%-20s%n", vo.getBoosterProdNo());
		}

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	
	
	public void selectByName() {
		try {
			Connection conn = JDBCTemplate.getConn();
			String sql = "SELECT * FROM BOOSTER_PRODUCT WHERE NAME = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			System.out.print("물품 이름: ");
			String inputName = Main.SC.nextLine();	
			pstmt.setString(1,"%" + inputName+ "%");
			
			ResultSet rs = pstmt.executeQuery();
			
			
			
		
			
			while(rs.next()) {
			String boosterProdNo = rs.getString("BOOSTER_PROD_NO");
			String name = rs.getString("NAME");
			String price = rs.getString("PRICE");
			String isDiscountinuedYn = rs.getString("IS_DISCOUNTINUED_YN");
			String description = rs.getString("DESCRIPTION");
			
			BoosterProductVo vo = new BoosterProductVo(boosterProdNo, name, price, isDiscountinuedYn, description);
			System.out.println(vo.getBoosterProdNo() + " | " + vo.getName() + " | " + vo.getPrice());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void write() {
		
		try {
		//conn
		Connection conn = JDBCTemplate.getConn();

		System.out.print("이름 : ");
		String name = Main.SC.nextLine();
		System.out.print("가격 : ");
		String price = Main.SC.nextLine();
		System.out.print("설명 : ");
		String description = Main.SC.nextLine();
		
		//SQL
		String sql = "INSERT INTO BOOSTER_PRODUCT (BOOSTER_PROD_NO, NAME, PRICE, DESCRIPTION) VALUES (SEQ_BOOSTER_PROD_NO.NEXTVAL, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, price);
		pstmt.setString(3, description);
		int result = pstmt.executeUpdate();
		
		//result
		if(result != 1) {
			System.out.println("등록실패 ");
			return;
		}
		System.out.println("등록 성공 ! ");
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}//class
	
	
}
