package game.helper.model.dto;

public class PopularityTemplateDTO {
	private int low;
	private int mid;
	private int high;
	
	public PopularityTemplateDTO(int low, int mid, int high) {
		super();
		this.low = low;
		this.mid = mid;
		this.high = high;
	}

	public PopularityTemplateDTO() {
		super();
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}
	
	
	
}
