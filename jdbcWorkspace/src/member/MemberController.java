package member;

import java.sql.Connection;
import java.sql.PreparedStatement;

import main.Main;
import util.JDBCTemplate;

public class MemberController {
	

	public void printMenu() {
		System.out.println("====MEMBER====");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.print("메뉴 번호: ");
		String menu = Main.SC.nextLine();

		switch (menu) {
		case "1":
			login();
			break;
		case "2":
			join();
			break;
		default:
			break;
		}
	}


	private void login() {
		String sql = "SELECT * FROM member WHERE ID = ? AND PWD = ?";

	}
	
	
	private void join() {
		try {
			Connection conn = JDBCTemplate.getConn();
			String sql = "INSERT INTO MEMBER(NO, ID, PWD, NICK) VALUES(SEQ_MEMBER_NO.NEXTVAL, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			System.out.print("id: ");
			String inputId = Main.SC.nextLine();
			System.out.print("pwd: ");
			String inputPwd = Main.SC.nextLine();
			System.out.print("nick: ");
			String inputNick = Main.SC.nextLine();
			pstmt.setString(1, inputId);
			pstmt.setString(2, inputPwd);
			pstmt.setString(3, inputNick);
			
			int r = pstmt.executeUpdate();
			
			if (r != 1) {
				System.out.println("회원가입 오류.");
				return;
			}
			System.out.println("회원가입 성공!");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
