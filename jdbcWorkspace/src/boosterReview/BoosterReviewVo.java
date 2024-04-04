package boosterReview;

public class BoosterReviewVo {
	
	
	
	
	public BoosterReviewVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BoosterReviewVo(String boosterReviewNo, String reviewTitle, String review, String boosterProdNo,
			String memberNo, String enrollDate, String quitYn) {
		super();
		this.boosterReviewNo = boosterReviewNo;
		this.reviewTitle = reviewTitle;
		this.review = review;
		this.boosterProdNo = boosterProdNo;
		this.memberNo = memberNo;
		this.enrollDate = enrollDate;
		this.quitYn = quitYn;
	}
	
	@Override
	public String toString() {
		return "BoosterReviewVo [boosterReviewNo=" + boosterReviewNo + ", reviewTitle=" + reviewTitle + ", review="
				+ review + ", boosterProdNo=" + boosterProdNo + ", memberNo=" + memberNo + ", enrollDate=" + enrollDate
				+ ", quitYn=" + quitYn + "]";
	}
	
	public String getBoosterReviewNo() {
		return boosterReviewNo;
	}
	public void setBoosterReviewNo(String boosterReviewNo) {
		this.boosterReviewNo = boosterReviewNo;
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
	public String getBoosterProdNo() {
		return boosterProdNo;
	}
	public void setBoosterProdNo(String boosterProdNo) {
		this.boosterProdNo = boosterProdNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getQuitYn() {
		return quitYn;
	}
	public void setQuitYn(String quitYn) {
		this.quitYn = quitYn;
	}
	private String boosterReviewNo;
	private String reviewTitle;
	private String review;
	private String boosterProdNo;
	private String memberNo;
	private String enrollDate;
	private String quitYn;

}
