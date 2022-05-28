package game.helper.model.dto;

import game.helper.model.Game;

public class GameResultDTO {
	private Game game;
	private int points;
	public GameResultDTO(Game game) {
		super();
		this.game = game;
		this.points = 0;
	}
	public GameResultDTO() {
		super();
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
}
