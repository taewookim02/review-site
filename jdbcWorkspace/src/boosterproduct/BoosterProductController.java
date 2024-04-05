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
		System.out.println("3. 물품 넣기");
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

			BoosterProductVo vo = null;

			while (rs.next()) {
				String boosterProdNo = rs.getString("BOOSTER_PROD_NO");
				String name = rs.getString("NAME");
				String price = rs.getString("PRICE");
				String isDiscountinuedYn = rs.getString("IS_DISCOUNTINUED_YN");
				String description = rs.getString("DESCRIPTION");

				vo = new BoosterProductVo();
				vo.setBoosterProdNo(boosterProdNo);
				vo.setName(name);
				vo.setPrice(price);
				vo.setIsDiscountinuedYn(isDiscountinuedYn);
				vo.setDescription(description);

				System.out.println("----------------------------");
				System.out.printf("제품번호 : " + vo.getBoosterProdNo());
				System.out.printf(" 제품명 : " + vo.getName());
				System.out.printf(" 가격 : " + vo.getPrice());
				System.out.printf(" 제품설명 : " + vo.getDescription());
				System.out.println("----------------------------");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void selectByName() {
		try {
			Connection conn = JDBCTemplate.getConn();
			String sql = "SELECT * FROM BOOSTER_PRODUCT WHERE NAME LIKE ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			System.out.println("물품 이름: ");
			String inputName = Main.SC.nextLine();
			pstmt.setString(1, "%" + inputName + "%");

			ResultSet rs = pstmt.executeQuery();

			BoosterProductVo vo = null;

			while (rs.next()) {
				String boosterProdNo = rs.getString("BOOSTER_PROD_NO");
				String name = rs.getString("NAME");
				String price = rs.getString("PRICE");
				String isDiscountinuedYn = rs.getString("IS_DISCOUNTINUED_YN");
				String description = rs.getString("DESCRIPTION");

				vo = new BoosterProductVo();
				vo.setBoosterProdNo(boosterProdNo);
				vo.setName(name);
				vo.setPrice(price);
				vo.setIsDiscountinuedYn(isDiscountinuedYn);
				vo.setDescription(description);

				System.out.println(vo.getBoosterProdNo() + " | " + vo.getName() + " | " + vo.getPrice());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void write() {

		try {
			// conn
			Connection conn = JDBCTemplate.getConn();

			System.out.print("이름 : ");
			String name = Main.SC.nextLine();
			System.out.print("가격 : ");
			String price = Main.SC.nextLine();
			System.out.print("설명 : ");
			String description = Main.SC.nextLine();

			// SQL
			String sql = "INSERT INTO BOOSTER_PRODUCT (BOOSTER_PROD_NO, NAME, PRICE, DESCRIPTION) VALUES (SEQ_BOOSTER_PROD_NO.NEXTVAL, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, price);
			pstmt.setString(3, description);
			int result = pstmt.executeUpdate();

			// result
			if (result != 1) {
				System.out.println("등록실패 ");
				return;
			}
			System.out.println("등록 성공 ! ");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// class

}
