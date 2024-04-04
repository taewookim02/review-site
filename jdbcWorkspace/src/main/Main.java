package main;

import java.util.Scanner;

import foodprod.FoodProdController;
import member.MemberController;
import member.MemberVo;
import normalProd.NormalProdController;

public class Main {
	public static final Scanner SC = new Scanner(System.in);
	public static MemberVo loginMember = null;

	public static void main(String[] args) throws Exception {
		FoodProdController fp = new FoodProdController();
		MemberController mc = new MemberController();
		NormalProdController nc = new NormalProdController();

		System.out.println("====MENU====");
		while (true) {
			System.out.println("1. MEMBER");
			System.out.println("2. food");
			System.out.println("3. normal");
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
				nc.printMenu();
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
