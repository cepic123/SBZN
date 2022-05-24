package game.helper.model;

import java.util.Date;

public class Review {
	private User user;
	private Game game;
	private Integer score;
	private Date time;
	
	public Review(User user, Game game, Integer score, Date time) {
		super();
		this.user = user;
		this.game = game;
		this.score = score;
		this.time = time;
	}
	
	public Review() {
		super();
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
