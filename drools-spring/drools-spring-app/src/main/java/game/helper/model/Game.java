package game.helper.model;

import game.helper.model.enums.Genre;

public class Game {
	private String name;
	private Genre genre;
	private DeveloperStudio studio;
	private double price;
	private double lenght;
	private boolean isMultiplayer;
	private boolean isOnline;
	
	public Game() {
		super();
	}

	public Game(String name, Genre genre, DeveloperStudio studio, double price, double lenght, boolean isMultiplayer,
			boolean isOnline) {
		super();
		this.name = name;
		this.genre = genre;
		this.studio = studio;
		this.price = price;
		this.lenght = lenght;
		this.isMultiplayer = isMultiplayer;
		this.isOnline = isOnline;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public DeveloperStudio getStudio() {
		return studio;
	}

	public void setStudio(DeveloperStudio studio) {
		this.studio = studio;
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
