package boosterproduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import main.Main;
import util.JDBCTemplate;
import util.TablePrinter;

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
			ArrayList<BoosterProductVo> arr = new ArrayList<>();


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
				
				arr.add(vo);
				
				/*
				 * this.boosterProdNo = boosterProdNo;
		this.name = name;
		this.price = price;
		this.isDiscountinuedYn = isDiscountinuedYn;
		this.description = description;
				 * */

				TablePrinter.printRecordsVertically( arr, new String[] {"boosterProdNo", "name", "price", "isDiscountinuedYn", "description"}
					,new String[] {"제품번호", "제품명", "가격", "단종 여부", "제품 설명"});
				
				
//				System.out.println("----------------------------");
//				System.out.printf("제품번호 : " + vo.getBoosterProdNo());
//				System.out.printf(" 제품명 : " + vo.getName());
//				System.out.printf(" 가격 : " + vo.getPrice());
//				System.out.printf(" 제품설명 : " + vo.getDescription());
//				System.out.println("----------------------------");
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
			ArrayList<BoosterProductVo> arr = new ArrayList<>();

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

				arr.add(vo);
				
				}
			TablePrinter.printRecordsVertically( arr, new String[] {"boosterProdNo", "name", "price", "isDiscountinuedYn", "description"}
			,new String[] {"제품번호", "제품명", "가격", "단종 여부", "제품 설명"});
			
			System.out.println("상품에 대해 리뷰를 작성하시겠습니까? Y/N");
			String reviewYn = Main.SC.nextLine();
			if(reviewYn.equals("Y")) {
				System.out.println("1");
				if (Main.loginMember == null) {
					System.out.println("로그인이 필요합니다.");
					return;
				}
				
					System.out.println("리뷰할 제품 번호 : ");
					String no = Main.SC.nextLine();
					System.out.println("리뷰 제목 : ");
					String reviewTitle = Main.SC.nextLine();
					System.out.println("리뷰 내용 : ");
					String review = Main.SC.nextLine();

					String sql2 = "INSERT INTO BOOSTER_REVIEW (BOOSTER_REVIEW_NO, REVIEW_TITLE, REVIEW, BOOSTER_PROD_NO, MEMBER_NO) VALUES (SEQ_BOOSTER_REVIEW_NO.NEXTVAL, ?, ?, ?, ?)";
					PreparedStatement pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setString(1, reviewTitle);
					pstmt2.setString(2, review);
					pstmt2.setString(3, no);
					pstmt2.setString(4, Main.loginMember.getNo());
					int result = pstmt2.executeUpdate();

					if (result != 1) {
						System.out.println("리뷰 작성 실패");
						return;
					}
					System.out.println("리뷰 등록 완료");
				}
			
		} catch (Exception e) {
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
				System.out.println("상품 등록 실패하셨습니다");
				return;
			}
			System.out.println("상품 등록 성공하셨습니다");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// class

}
