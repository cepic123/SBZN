package game.helper.model.dto;

import java.util.List;

import game.helper.model.enums.Genre;

public class GameDTO {
	private String name;
	private List<Genre> genres;
	private Integer studioId;
	private double price;
	private double lenght;
	private boolean isMultiplayer;
	private boolean isOnline;
	public GameDTO(String name, List<Genre> genres, Integer studioId, double price, double lenght,
			boolean isMultiplayer, boolean isOnline) {
		super();
		this.name = name;
		this.genres = genres;
		this.studioId = studioId;
		this.price = price;
		this.lenght = lenght;
		this.isMultiplayer = isMultiplayer;
		this.isOnline = isOnline;
	}
	public GameDTO() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	public Integer getStudioId() {
		return studioId;
	}
	public void setStudioId(Integer studioId) {
		this.studioId = studioId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getLenght() {
		return lenght;
	}
	public void setLenght(double lenght) {
		this.lenght = lenght;
	}
	public boolean isMultiplayer() {
		return isMultiplayer;
	}
	public void setMultiplayer(boolean isMultiplayer) {
		this.isMultiplayer = isMultiplayer;
	}
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
}
