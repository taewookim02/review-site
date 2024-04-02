package foodprod;

import java.sql.Connection;
import java.sql.DriverManager;

import main.Main;

public class FoodProd {
	
public void printMenu() throws Exception{
		
		System.out.println("----메뉴 선택----");
		System.out.println("1. 물품 조회 (최신순)");
		System.out.println("2. 물품 조회 (상품명)");
		System.out.println("3. 물품 넣기");
		System.out.print("번호 입력 : ");
		
		String num = Main.SC.nextLine();
		
		switch(num) {
		
		case "1": lookUpRecent(); break;
		case "2": lookUpName(); break;
		case "3": addFood(); break;
		default : System.out.println("잘못된 번호");
		
		}
		
	}
	
public void lookUpRecent() throws Exception{
	
	
	
	
}

public void lookUpName() throws Exception{
	
	
	
}

public void addFood() throws Exception{
	
	
} 



}
