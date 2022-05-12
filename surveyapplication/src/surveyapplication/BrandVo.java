package surveyapplication;

public class BrandVo {
	private int brandNumber;
	private String title;

	public BrandVo(int num, String title) {
		setBrandNumber(num);
		setTitle(title);
	}

	public int getBrandNumber() {
		return brandNumber;
	}

	public void setBrandNumber(int brandNumber) {
		this.brandNumber = brandNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return getBrandNumber() + ". " + getTitle();
	}
}
