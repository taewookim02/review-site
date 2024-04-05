package main;

import java.util.Scanner;

import AnnouncementBoard.AnnouncementBoard;
import board.BoardController;
import boosterReview.BoosterReviewController;
import boosterproduct.BoosterProductController;
import foodprod.FoodProdController;
import foodreview.FoodReviewController;
import member.MemberController;
import member.MemberVo;
import normalProd.NormalProdController;
import normalReview.NormalReviewController;

public class Main {
	public static final Scanner SC = new Scanner(System.in);
	public static MemberVo loginMember = null;

	public static void main(String[] args) throws Exception {
		MemberController mc = new MemberController();
		FoodProdController fp = new FoodProdController();
		FoodReviewController frp = new FoodReviewController();
		NormalProdController nc = new NormalProdController();
		NormalReviewController nrc = new NormalReviewController();
		BoosterProductController bpc = new BoosterProductController();
		BoosterReviewController brc = new BoosterReviewController();
		AnnouncementBoard ab = new AnnouncementBoard();
		BoardController bc = new BoardController();

		while (true) {
			System.out.println("====MENU====");
			System.out.println("1. MEMBER");
			System.out.println("2. FOOD PRODUCT");
			System.out.println("3. FOOD REVIEW");
			System.out.println("4. 자유게시판");
			System.out.println("5. 공지사항 게시판");
			System.out.println("6. NORMAL PROD");
			System.out.println("7. NORMAL REVIEW");
			System.out.println("8. BOOSTER PRODUCT");
			System.out.println("9. BOOSTER REVIEW");
			System.out.println("0. 종료하기");

			System.out.print("메뉴번호: ");
			String menu = Main.SC.nextLine();

			switch (menu) {
			case "1":
				mc.printMenu();
				break;
			case "2":
				fp.printMenu();
				break;
			case "3":
				frp.printMenu();
				break;
			case "4":
				bc.printBoardMenu(); // D
				break;
			case "5":
				ab.printMenu(); // D
				break;
			case "6":
				nc.printMenu();
				break;
			case "7":
				nrc.printMenu();
				break;
			case "8":
				bpc.printMenu();
				break;
			case "9":
				brc.printMenu();
				break;
			case "0":
				System.out.println("프로그램 종료");
				return;

			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		}
	}

}
