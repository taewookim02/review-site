package normalReview;

public class NormalReviewVo {
	
	private String normalReviewlNo;
	private String normalReviewTilte;
	private String normalReview;
	private String normalProdNo;
	private String writerNo;
	private String enrollDate;
	private String quityn;
	public NormalReviewVo(String normalReviewlNo, String normalReviewTilte, String normalReview, String normalProdNo,
			String writerNo, String enrollDate, String quityn) {
		super();
		this.normalReviewlNo = normalReviewlNo;
		this.normalReviewTilte = normalReviewTilte;
		this.normalReview = normalReview;
		this.normalProdNo = normalProdNo;
		this.writerNo = writerNo;
		this.enrollDate = enrollDate;
		this.quityn = quityn;
	}
	public NormalReviewVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNormalReviewlNo() {
		return normalReviewlNo;
	}
	public void setNormalReviewlNo(String normalReviewlNo) {
		this.normalReviewlNo = normalReviewlNo;
	}
	public String getNormalReviewTilte() {
		return normalReviewTilte;
	}
	public void setNormalReviewTilte(String normalReviewTilte) {
		this.normalReviewTilte = normalReviewTilte;
	}
	public String getNormalReview() {
		return normalReview;
	}
	public void setNormalReview(String normalReview) {
		this.normalReview = normalReview;
	}
	public String getNormalProdNo() {
		return normalProdNo;
	}
	public void setNormalProdNo(String normalProdNo) {
		this.normalProdNo = normalProdNo;
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
		return "NormalReviewVo [normalReviewlNo=" + normalReviewlNo + ", normalReviewTilte=" + normalReviewTilte
				+ ", normalReview=" + normalReview + ", normalProdNo=" + normalProdNo + ", writerNo=" + writerNo
				+ ", enrollDate=" + enrollDate + ", quityn=" + quityn + "]";
	}
	
	
}
