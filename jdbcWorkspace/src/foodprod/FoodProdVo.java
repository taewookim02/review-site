package foodprod;

public class FoodProdVo {
	
	
	private String foodProdNo;
	private String foodProdName;
	private String price;
	private String isDidcount;
	private String description;
	
	public FoodProdVo(String foodProdNo, String foodProdName, String price, String isDidcount, String description) {
		super();
		this.foodProdNo = foodProdNo;
		this.foodProdName = foodProdName;
		this.price = price;
		this.isDidcount = isDidcount;
		this.description = description;
	}

	public FoodProdVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFoodProdNo() {
		return foodProdNo;
	}

	public void setFoodProdNo(String foodProdNo) {
		this.foodProdNo = foodProdNo;
	}

	public String getFoodProdName() {
		return foodProdName;
	}

	public void setFoodProdName(String foodProdName) {
		this.foodProdName = foodProdName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getIsDidcount() {
		return isDidcount;
	}

	public void setIsDidcount(String isDidcount) {
		this.isDidcount = isDidcount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "FoodProdVo [foodProdNo=" + foodProdNo + ", foodProdName=" + foodProdName + ", price=" + price
				+ ", isDidcount=" + isDidcount + ", description=" + description + "]";
	}
	
	
	
}
