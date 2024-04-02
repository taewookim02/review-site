package normalProd;

public class NormalProdVo {
	
	private String normaProdlNo;
	private String normalProdName;
	private String price;
	private String isDiscount;
	private String description;
	public String getNormaProdlNo() {
		return normaProdlNo;
	}
	public void setNormaProdlNo(String normaProdlNo) {
		this.normaProdlNo = normaProdlNo;
	}
	public String getNormalProdName() {
		return normalProdName;
	}
	public void setNormalProdName(String normalProdName) {
		this.normalProdName = normalProdName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getIsDiscount() {
		return isDiscount;
	}
	public void setIsDiscount(String isDiscount) {
		this.isDiscount = isDiscount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public NormalProdVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "NormalProdVo [normaProdlNo=" + normaProdlNo + ", normalProdName=" + normalProdName + ", price=" + price
				+ ", isDiscount=" + isDiscount + ", description=" + description + "]";
	}
	public NormalProdVo(String normaProdlNo, String normalProdName, String price, String isDiscount,
			String description) {
		super();
		this.normaProdlNo = normaProdlNo;
		this.normalProdName = normalProdName;
		this.price = price;
		this.isDiscount = isDiscount;
		this.description = description;
	}
	
}
