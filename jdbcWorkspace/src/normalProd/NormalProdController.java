package normalProd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import main.Main;
import util.JDBCTemplate;

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
			System.out.println("잘못입력하셨습니다");
			return;
		}

	}

	private void lookUpRecent() throws Exception {

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
			System.out.println("게시물 조회 실패");
			return;
		}

		System.out.printf("%-5s | %-15s | %-10s | %-5s | %-20s%n", "번호", "제품 이름", "가격", "단종 여부", "제품 설명");

		for (NormalProdVo vo : volist) {
			System.out.printf("%-6s | %-15s | %-10s | %-8s | %-20s%n", vo.getNormaProdlNo(), vo.getNormalProdName(),
					vo.getPrice(), vo.getIsDiscount(), vo.getDescription());
		}

	}

	private void addNormal() throws Exception {
//		if(Main.loginMember == null) {
//			System.out.println("로그인 먼저 이용해주세요");
//			return;
//		}
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
			System.out.println("상품등록 실패하셨습니다");
			return;
		}

		System.out.println("상품등록 성공하셨습니다");

	}
}
