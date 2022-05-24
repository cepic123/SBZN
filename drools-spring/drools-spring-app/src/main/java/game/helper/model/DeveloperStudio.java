package game.helper.model;

import java.util.List;

public class DeveloperStudio {
	private String name;
	private String address;
	private List<Game> games;
	
	public DeveloperStudio(String name, String address, List<Game> games) {
		super();
		this.name = name;
		this.address = address;
		this.games = games;
	}

	public DeveloperStudio() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
}
