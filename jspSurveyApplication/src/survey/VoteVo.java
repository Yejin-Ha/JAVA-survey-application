package survey;

public class VoteVo {
	private String brand;
	private int vote;

	public VoteVo() {
	}

	public VoteVo(String brand, int vote) {
		setBrand(brand);
		setVote(vote);
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	@Override
	public String toString() {
		return getBrand() + " . " + getVote();
	}

}
