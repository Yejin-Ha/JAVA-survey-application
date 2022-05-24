package survey;

public class BrandVo {
	private int brandNum;
	private String brand;

	public BrandVo() {
	}

	public BrandVo(int num, String brand) {
		setBrand(brand);
		setBrandNum(num);
	}

	public int getBrandNum() {
		return brandNum;
	}

	public void setBrandNum(int brandNum) {
		this.brandNum = brandNum;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return getBrandNum() + ". " + getBrand();
	}

}
