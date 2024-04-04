package main;

import java.util.Scanner;

import AnnouncementBoard.AnnouncementBoard;
import board.BoardController;
import boosterproduct.BoosterProductController;
import foodprod.FoodProd;
import member.MemberController;
import member.MemberVo;

public class Main {
	public static final Scanner SC = new Scanner(System.in);
	public static MemberVo loginMember = null;

	public static void main(String[] args) throws Exception {
		MemberController mc = new MemberController();
		BoardController bc = new BoardController();
		AnnouncementBoard ab = new AnnouncementBoard();
		FoodProd fp = new FoodProd();
		BoosterProductController bpc = new BoosterProductController();

		while (true) {
			System.out.println("====MENU====");
			System.out.println("1. MEMBER");
			System.out.println("2. food");
			System.out.println("3. 자유게시판");
			System.out.println("4. 공지사항 게시판");
			System.out.println("5. BOOSTER");
			System.out.println("9. 종료하기");

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
				bc.printBoardMenu();
				break;
			case "4":
				ab.printMenu();
				break;
			case "5":
				bpc.printMenu();
				break;

			case "9":
				System.out.println("프로그램 종료");
				return;

			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		}
	}

}
