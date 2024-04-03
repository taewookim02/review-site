package foodreview;

public class FoodReviewVo {
	
	
	private String foodReviewNo;
	private String reviewTitle;
	private String review;
	private String foodProdNo;
	private String writerNo;
	private String enrollDate;
	private String quityn;
	
	public FoodReviewVo(String foodReviewNo, String reviewTitle, String review, String foodProdNo, String writerNo,
			String enrollDate, String quityn) {
		super();
		this.foodReviewNo = foodReviewNo;
		this.reviewTitle = reviewTitle;
		this.review = review;
		this.foodProdNo = foodProdNo;
		this.writerNo = writerNo;
		this.enrollDate = enrollDate;
		this.quityn = quityn;
	}

	public FoodReviewVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFoodReviewNo() {
		return foodReviewNo;
	}

	public void setFoodReviewNo(String foodReviewNo) {
		this.foodReviewNo = foodReviewNo;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getFoodProdNo() {
		return foodProdNo;
	}

	public void setFoodProdNo(String foodProdNo) {
		this.foodProdNo = foodProdNo;
	}

	public String getWriterNo() {
		return writerNo;
	}

	public void setWriterNo(String writerNo) {
		this.writerNo = writerNo;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getQuityn() {
		return quityn;
	}

	public void setQuityn(String quityn) {
		this.quityn = quityn;
	}

	@Override
	public String toString() {
		return "FoodReviewVo [foodReviewNo=" + foodReviewNo + ", reviewTitle=" + reviewTitle + ", review=" + review
				+ ", foodProdNo=" + foodProdNo + ", writerNo=" + writerNo + ", enrollDate=" + enrollDate + ", quityn="
				+ quityn + "]";
	}
	
		
	
	
	
	
}
