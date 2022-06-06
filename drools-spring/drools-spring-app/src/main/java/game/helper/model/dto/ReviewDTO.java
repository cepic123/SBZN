package game.helper.model.dto;

public class ReviewDTO {
	private Integer userId;
	private Integer gameId;
	private Integer score;
	public ReviewDTO(Integer userId, Integer gameId, Integer score) {
		super();
		this.userId = userId;
		this.gameId = gameId;
		this.score = score;
	}
	public ReviewDTO() {
		super();
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getGameId() {
		return gameId;
	}
	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
}
