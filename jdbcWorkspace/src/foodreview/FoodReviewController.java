package foodreview;

import java.sql.Connection;
import java.sql.DriverManager;

import main.Main;
import util.JDBCTemplate;


public class FoodReviewController {
	
	public void printMenu() throws Exception{
		
		System.out.println("----메뉴 선택----");
		System.out.println("1. 후기 작성");
		System.out.println("2. 게시물 삭제");
		System.out.println("3. 게시물 제목 수정");
		System.out.println("4. 게시물 내용 수정");
		System.out.println("5. 게시물 목록 조회");
		System.out.println("6. 게시물 상세 조회 (작성자명)");
		System.out.print("번호 입력 : ");
		
		String num = Main.SC.nextLine();
		
		switch(num) {
		
		case "1": write(); break;
		case "2": delete(); break;
		case "3": editTitle(); break;
		case "4": editContent(); break;
		case "5": lookUp(); break;
		case "6": searchWriter(); break;
		default : System.out.println("잘못된 번호");
		
		}
		
	}
	
	
	public void write() throws Exception{
		
		
		
		
	}
	
	
	public void delete() throws Exception{
		
		
	}
	
	public void editTitle() throws Exception{
		
		
	}
	
	public void editContent() throws Exception{
		
		
	}
	
	public void lookUp() throws Exception{
		
		
	}
	
	public void searchWriter() throws Exception{
		
		
	}
	

	
}
