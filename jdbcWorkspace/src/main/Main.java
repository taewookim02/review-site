package main;

import java.util.Scanner;

import AnnouncementBoard.AnnouncementBoard;
import board.BoardController;
import member.MemberController;
import member.MemberVo;

public class Main {
	public static final Scanner SC = new Scanner(System.in);
	public static MemberVo loginMember = null;

	public static void main(String[] args) throws Exception {
		MemberController mc = new MemberController();
		BoardController bc = new BoardController();
		AnnouncementBoard ab = new AnnouncementBoard();

		System.out.println("====MENU====");
		while (true) {
			System.out.println("1. MEMBER");
			System.out.println("2. 자유게시판");
			System.out.println("3. 공지사항 게시판");
			System.out.println("9. 종료하기");

			System.out.print("메뉴번호: ");
			String menu = Main.SC.nextLine();

			switch (menu) {
			case "1":
				mc.printMenu();
				break;
			case "2":
				bc.printBoardMenu();
				break;
			case "3":
				ab.printMenu();
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
