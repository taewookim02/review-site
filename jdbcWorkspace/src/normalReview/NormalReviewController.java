package normalReview;

import main.Main;

public class NormalReviewController {

	public void printMenu()throws Exception {
		
		System.out.println("번호를 선택해주세요");
		System.out.println("1. 게시물 목록 조회;");
		System.out.println("2. 게시물 작성자명 조회");
		System.out.println("3. 게시물 제목 수정");
		System.out.println("4. 게시물 내용 수정");
		System.out.println("5. 구매 후기 작성");
		System.out.println("6. 게시물 삭제");

		System.out.print("번호를 입력하세요 : ");
		String num = Main.SC.nextLine();
		
		switch(num) {
		
		case "1" : lookUp(); break;
		case "2" : searchWriter(); break;
		case "3" : editTitle(); break;
		case "5" : editContent(); break;
		case "4" : write(); break;
		case "6" : delete();  break;
		default : System.out.println("잘못입력하셨습니다");
		}
	
	}
	
	private void lookUp() throws Exception{
		
	}
	
	private void searchWriter() throws Exception{
		
	}
	
	private void editTitle() throws Exception{
		
	}
	
	private void editContent() throws Exception{
		
	}
	
	private void write() throws Exception{
		
	}
	
	private void delete() throws Exception{
		
	}
	
	
	
}
