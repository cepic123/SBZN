package game.helper.model.dto;

import java.util.List;

import game.helper.model.enums.Genre;

public class ParametersDTO {
	private List<Genre> genres;
	private List<String> studios;
	private double lenght;
	private double price;
	private boolean isMultiplayer;
	private boolean isOnline;
	public ParametersDTO(List<Genre> genres, List<String> studios, double lenght, double price, boolean isMultiplayer,
			boolean isOnline) {
		super();
		this.genres = genres;
		this.studios = studios;
		this.lenght = lenght;
		this.price = price;
		this.isMultiplayer = isMultiplayer;
		this.isOnline = isOnline;
	}
	public ParametersDTO() {
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
	public double getLenght() {
		return lenght;
	}
	public void setLenght(double lenght) {
		this.lenght = lenght;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
