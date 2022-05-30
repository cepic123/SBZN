package game.helper.model.dto;

import java.util.List;

import game.helper.model.enums.Genre;

public class UserHistoryDTO {
	private List<Genre> genres;
	private List<String> studios;
	private double avgLength;
	private double avgPrice;
	public UserHistoryDTO(List<Genre> genres, List<String> studios, double avgLength, double avgPrice) {
		super();
		this.genres = genres;
		this.studios = studios;
		this.avgLength = avgLength;
		this.avgPrice = avgPrice;
	}
	public UserHistoryDTO() {
		super();
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	public List<String> getStudios() {
		return studios;
	}
	public void setStudios(List<String> studios) {
		this.studios = studios;
	}
	public double getAvgLength() {
		return avgLength;
	}
	public void setAvgLength(double avgLength) {
		this.avgLength = avgLength;
	}
	public double getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}
	@Override
	public String toString() {
		return "UserHistoryDTO [genres=" + genres + ", studios=" + studios + ", avgLength=" + avgLength + ", avgPrice="
				+ avgPrice + "]";
	}
	
	
}
