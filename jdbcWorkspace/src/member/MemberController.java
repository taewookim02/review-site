package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
		try {
			Connection conn = JDBCTemplate.getConn();
			String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			System.out.print("아이디: ");
			String inputId = Main.SC.nextLine();
			System.out.print("비밀번호: ");
			String inputPwd = Main.SC.nextLine();
			pstmt.setString(1, inputId);
			pstmt.setString(2, inputPwd);
			
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				System.out.println("로그인 실패.");
				return;
			}
			
			String no = rs.getString("NO");
			String id = rs.getString("ID");
			String pwd = rs.getString("PWD");
			String nick = rs.getString("NICK");
			String joindate = rs.getString("JOIN_DATE");
			String modifyDate = rs.getString("MODIFY_DATE");
			String quitYn = rs.getString("QUIT_YN");
			String adminYn = rs.getString("ADMIN_YN");
			
			MemberVo vo = new MemberVo(no, id, pwd, nick, joindate, modifyDate, quitYn, adminYn);
			Main.loginMember = vo;
			System.out.println(vo.getNick() + "님, 환영합니다.");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void join() {
		try {
			Connection conn = JDBCTemplate.getConn();
			String sql = "INSERT INTO MEMBER(NO, ID, PWD, NICK) VALUES(SEQ_MEMBER_NO.NEXTVAL, ?, ?, ?)";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			System.out.print("아이디: ");
			String inputId = Main.SC.nextLine();
			System.out.print("비밀번호: ");
			String inputPwd = Main.SC.nextLine();
			System.out.print("닉네임: ");
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
