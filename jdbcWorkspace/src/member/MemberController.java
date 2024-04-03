package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import main.Main;
import util.JDBCTemplate;

public class MemberController {

	public void printMenu() {

		if (Main.loginMember == null) {
			System.out.println("====MEMBER====");
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("9. 이전 메뉴로 돌아가기");

			System.out.print("메뉴 번호: ");
			String menu = Main.SC.nextLine();

			switch (menu) {
			case "1":
				login();
				break;
			case "2":
				join();
				break;
			case "9":
				System.out.println("=============");
				System.out.println("이전 메뉴로..");
				System.out.println("=============");
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		} else if (Main.loginMember.getAdmin_yn().equals("Y")) {
			// 관리자멤버 일 때
			System.out.println("====관리자메뉴====");
			System.out.println("1. 로그아웃");
			System.out.println("2. 비밀번호 변경");
			System.out.println("3. 닉네임 변경");
			System.out.println("4. 회원 전체 목록");
			System.out.println("5. 회원 상세 조회");
			System.out.println("9. 이전 메뉴로 돌아가기");

			System.out.print("메뉴 번호: ");
			String menu = Main.SC.nextLine();
			switch (menu) {
			case "1":
				// 로그아웃
				logout();
				break;
			case "2":
				// 정보 수정 (비번 변경)
				changePwd();
				break;
			case "3":
				// 정보 수정 (닉네임 변경)
				changeNick();
				break;
			case "4":
				// 회원 전체 목록 조회 (관리자 전용)
				selectAllMembers();
				break;
			case "5":
				// 회원 상세 조회 (관리자 전용)
				selectOneMember();
				break;
			case "9":
				System.out.println("=============");
				System.out.println("이전 메뉴로..");
				System.out.println("=============");
				return;

			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}

		} else if (Main.loginMember != null) {
			// 그냥 회원
			System.out.println("====" + Main.loginMember.getNick() + "====");
			System.out.println("1. 로그아웃");
			System.out.println("2. 비밀번호 변경");
			System.out.println("3. 닉네임 변경");
			System.out.println("4. 회원탈퇴");
			System.out.println("9. 이전 메뉴로 돌아가기");

			System.out.print("메뉴 번호: ");
			String menu = Main.SC.nextLine();

			switch (menu) {
			case "1":
				// 로그아웃
				logout();
				break;
			case "2":
				// 정보 수정 (비번 변경)
				changePwd();
				break;
			case "3":
				// 정보 수정 (닉네임 변경)
				changeNick();
				break;
			case "4":
				// 회원탈퇴
				deleteMember();
				break;
			case "9":
				System.out.println("=============");
				System.out.println("이전 메뉴로..");
				System.out.println("=============");
				return;

			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		}
	}

	private void selectOneMember() {
		try {
			Connection conn = JDBCTemplate.getConn();
			String sql = "SELECT * FROM MEMBER WHERE NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			System.out.print("조회할 회원 번호: ");
			String inputNo = Main.SC.nextLine();
			pstmt.setString(1, inputNo);

			ResultSet rs = pstmt.executeQuery();
			MemberVo vo = null;
			if (rs.next()) {
				String no = rs.getString("NO");
				String id = rs.getString("ID");
				String pwd = rs.getString("PWD");
				String nick = rs.getString("NICK");
				String joinDate = rs.getString("JOIN_DATE");
				String modifyDate = rs.getString("MODIFY_DATE");
				String quitYn = rs.getString("QUIT_YN");
				String adminYn = rs.getString("ADMIN_YN");
				vo = new MemberVo(no, id, pwd, nick, joinDate, modifyDate, quitYn, adminYn);
			}
			
			if (vo == null) {
				System.out.println("회원 조회 실패");
				return;
			}

			System.out.println(vo);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void selectAllMembers() {
		try {
			Connection conn = JDBCTemplate.getConn();
			String sql = "SELECT * FROM MEMBER ORDER BY NO ASC";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			ArrayList<MemberVo> voList = new ArrayList<>();

			while (rs.next()) {
				String no = rs.getString("NO");
				String id = rs.getString("ID");
				String pwd = rs.getString("PWD");
				String nick = rs.getString("NICK");
				String joinDate = rs.getString("JOIN_DATE");
				String modifyDate = rs.getString("MODIFY_DATE");
				String quitYn = rs.getString("QUIT_YN");
				String adminYn = rs.getString("ADMIN_YN");

				MemberVo vo = new MemberVo(no, id, pwd, nick, joinDate, modifyDate, quitYn, adminYn);
				voList.add(vo);
			}

			if (voList.isEmpty()) {
				System.out.println("=============");
				System.out.println("회원 목록 조회 실패");
				System.out.println("=============");
				return;
			}

			for (MemberVo reVo : voList) {
				System.out.println(reVo);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void deleteMember() {
		try {
			String pwd = Main.loginMember.getPwd();
			System.out.println("=============");
			System.out.println("탈퇴하실려면 비밀번호를 입력해주세요.");
			System.out.print("비밀번호: ");
			String inputPwd = Main.SC.nextLine();

			if (!inputPwd.equals(pwd)) {
				System.out.println("=============");
				System.out.println("비밀번호가 다릅니다.");
				System.out.println("=============");
				return;
			}

			Connection conn = JDBCTemplate.getConn();
			String sql = "UPDATE MEMBER SET QUIT_YN = 'Y', MODIFY_DATE = SYSDATE WHERE NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, Main.loginMember.getNo());

			int r = pstmt.executeUpdate();

			if (r != 1) {
				System.out.println("=============");
				System.out.println("회원탈퇴 실패");
				System.out.println("=============");
				return;
			}

			System.out.println("=============");
			System.out.println("회원탈퇴 성공");
			System.out.println("=============");
			logout();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void changeNick() {
		try {
			Connection conn = JDBCTemplate.getConn();
			String sql = "UPDATE MEMBER SET NICK = ?, MODIFY_DATE = SYSDATE WHERE NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			System.out.print("새로운 닉네임: ");
			String inputNick = Main.SC.nextLine();
			pstmt.setString(1, inputNick);
			pstmt.setString(2, Main.loginMember.getNo());

			int r = pstmt.executeUpdate();

			if (r != 1) {
				System.out.println("닉네임 변경 실패");
				return;
			}

			System.out.println("닉네임 변경 성공!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void changePwd() {
		try {
			Connection conn = JDBCTemplate.getConn();
			String sql = "UPDATE MEMBER SET PWD = ?, MODIFY_DATE = SYSDATE WHERE NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			System.out.print("새로운 비밀번호: ");
			String inputPwd = Main.SC.nextLine();
			pstmt.setString(1, inputPwd);
			pstmt.setString(2, Main.loginMember.getNo());

			int r = pstmt.executeUpdate();

			if (r != 1) {
				System.out.println("=============");
				System.out.println("비밀번호 변경 실패");
				System.out.println("=============");
				return;
			}

			System.out.println("=============");
			System.out.println("비밀번호 변경 성공!");
			System.out.println("=============");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void logout() {
		Main.loginMember = null;
		System.out.println("=============");
		System.out.println("로그아웃 완료.");
		System.out.println("=============");
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
				System.out.println("=============");
				System.out.println("로그인 실패.");
				System.out.println("=============");
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

			if (vo.getQuit_yn().equals("Y")) {
				// 탈퇴한 회원일 때
				System.out.println("=============");
				System.out.println("탈퇴한 회원입니다.");
				System.out.println("=============");
				return;
			}

			Main.loginMember = vo;

			System.out.println("=============");
			System.out.println(vo.getNick() + "님, 환영합니다.");
			System.out.println("=============");

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
				System.out.println("=============");
				System.out.println("회원가입 오류.");
				System.out.println("=============");
				return;
			}
			System.out.println("=============");
			System.out.println("회원가입 성공!");
			System.out.println("=============");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
