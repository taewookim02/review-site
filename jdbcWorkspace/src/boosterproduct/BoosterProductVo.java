package boosterproduct;

public class BoosterProductVo {

	public BoosterProductVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoosterProductVo(String boosterProdNo, String name, String price, String isDiscountinuedYn,
			String description) {
		super();
		this.boosterProdNo = boosterProdNo;
		this.name = name;
		this.price = price;
		this.isDiscountinuedYn = isDiscountinuedYn;
		this.description = description;
	}

	@Override
	public String toString() {
		return "BoosterProductVo [boosterProdNo=" + boosterProdNo + ", name=" + name + ", price=" + price
				+ ", isDiscountinuedYn=" + isDiscountinuedYn + ", description=" + description + "]";
	}

	public String getBoosterProdNo() {
		return boosterProdNo;
	}

	public void setBoosterProdNo(String boosterProdNo) {
		this.boosterProdNo = boosterProdNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getIsDiscountinuedYn() {
		return isDiscountinuedYn;
	}

	public void setIsDiscountinuedYn(String isDiscountinuedYn) {
		this.isDiscountinuedYn = isDiscountinuedYn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String boosterProdNo;
	private String name;
	private String price;
	private String isDiscountinuedYn;
	private String description;

}
