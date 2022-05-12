package surveyapplication;

public class VoteVo {
	private String title;
	private int cnt;

	public VoteVo(String title, int cnt) {
		setCnt(cnt);
		setTitle(title);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return title + "\t---> " + cnt + "Έν";
	}
}
