package main;

import java.util.Scanner;

import foodprod.FoodProd;
import foodreview.FoodReview;
import member.MemberController;
import member.MemberVo;

public class Main {
	public static final Scanner SC = new Scanner(System.in);
	public static MemberVo loginMember = null;

	public static void main(String[] args) throws Exception {
		MemberController mc = new MemberController();
		FoodProd fp = new FoodProd();
		FoodReview fr = new FoodReview();
		System.out.println("====MENU====");
		while (true) {
			System.out.println("1. MEMBER");
			System.out.println("2. food product");
			System.out.println("3. food review");
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
			case "3" : 
				fr.printMenu();
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
