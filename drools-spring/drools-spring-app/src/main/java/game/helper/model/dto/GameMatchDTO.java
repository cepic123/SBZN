package game.helper.model.dto;

import game.helper.model.Game;
import game.helper.model.Review;

public class GameMatchDTO {
	private Game game;
	private Integer noUserLow;
	private Integer noUserMid;
	private Integer noUserHigh;
	private boolean gLow;
	private boolean gMid;
	private boolean gHigh;
	private double rating;
	
	public GameMatchDTO(Game game, Integer noUserLow, Integer noUserMid, Integer noUserHigh) {
		super();
		this.game = game;
		this.noUserLow = noUserLow;
		this.noUserMid = noUserMid;
		this.noUserHigh = noUserHigh;
	}
	public GameMatchDTO() {
		super();
		this.noUserLow = 0;
		this.noUserMid = 0;
		this.noUserHigh = 0;
		this.gLow = false;
		this.gMid = false;
		this.gHigh = false;
	}
	
	public double getGrade() {
        double sum = 0;
        for(Review review : this.game.getReviews()) {
            sum += review.getScore();
        }
        return sum / (double) this.game.getReviews().size();
    }
	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public Integer getNoUserLow() {
		return noUserLow;
	}
	public void setNoUserLow(Integer noUserLow) {
		this.noUserLow = noUserLow;
	}
	public Integer getNoUserMid() {
		return noUserMid;
	}
	public void setNoUserMid(Integer noUserMid) {
		this.noUserMid = noUserMid;
	}
	public Integer getNoUserHigh() {
		return noUserHigh;
	}
	public void setNoUserHigh(Integer noUserHigh) {
		this.noUserHigh = noUserHigh;
	}
	public boolean isgLow() {
		return gLow;
	}
	public void setgLow(boolean gLow) {
		this.gLow = gLow;
	}
	public boolean isgMid() {
		return gMid;
	}
	public void setgMid(boolean gMid) {
		this.gMid = gMid;
	}
	public boolean isgHigh() {
		return gHigh;
	}
	public void setgHigh(boolean gHigh) {
		this.gHigh = gHigh;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
}
