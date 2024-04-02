package normalProd;

import main.Main;

public class NormalProdController {
	
	public void printMenu()throws Exception {
		
		System.out.println("번호를 선택해주세요");
		System.out.println("1.상품명 등록");
		System.out.println("2.상품명으로 검색");
		System.out.println("3.상품명 최신순으로 조회");
		
		System.out.print("번호를 입력하세요 : ");
		String num = Main.SC.nextLine();
		
		switch(num) {
		
		case "1" : addNormal();  break;
		case "2" : lookUpname(); break;
		case "3" : lookUpRecent(); break;
		default : System.out.println("잘못입력하셨습니다"); return;
		}
		
	}
	
	private void lookUpRecent()throws Exception {
		
	}
	
	private void lookUpname()throws Exception {
		
	}
	
	
	private void addNormal()throws Exception {
		
	}
	
	

}
