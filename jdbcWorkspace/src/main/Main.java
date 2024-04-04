package main;

import java.util.Scanner;

import boosterproduct.BoosterProductController;
import foodprod.FoodProd;
import member.MemberController;
import member.MemberVo;

public class Main {
	public static final Scanner SC = new Scanner(System.in);
	public static MemberVo loginMember = null;

	public static void main(String[] args) throws Exception {
		MemberController mc = new MemberController();
		BoosterProductController bpc = new BoosterProductController();
		FoodProd fp = new FoodProd();

		while (true) {
			System.out.println("====MENU====");
			System.out.println("1. MEMBER");
			System.out.println("2. food");
			System.out.println("4. BOOSTER");
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
			case "4":
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
