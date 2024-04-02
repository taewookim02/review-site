package member;

public class MemberVo {
	private String no;
	private String id;
	private String pwd;
	private String nick;
	private String join_date;
	private String modify_date;
	private String quit_yn;
	private String admin_yn;

	public MemberVo() {
		super();
	}

	public MemberVo(String no, String id, String pwd, String nick, String join_date, String modify_date, String quit_yn,
			String admin_yn) {
		super();
		this.no = no;
		this.id = id;
		this.pwd = pwd;
		this.nick = nick;
		this.join_date = join_date;
		this.modify_date = modify_date;
		this.quit_yn = quit_yn;
		this.admin_yn = admin_yn;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getJoin_date() {
		return join_date;
	}

	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}

	public String getModify_date() {
		return modify_date;
	}

	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}

	public String getQuit_yn() {
		return quit_yn;
	}

	public void setQuit_yn(String quit_yn) {
		this.quit_yn = quit_yn;
	}

	public String getAdmin_yn() {
		return admin_yn;
	}

	public void setAdmin_yn(String admin_yn) {
		this.admin_yn = admin_yn;
	}

	@Override
	public String toString() {
		return "MemberVo [no=" + no + ", id=" + id + ", pwd=" + pwd + ", nick=" + nick + ", join_date=" + join_date
				+ ", modify_date=" + modify_date + ", quit_yn=" + quit_yn + ", admin_yn=" + admin_yn + "]";
	}

}
