package game.helper.model.dto;

public class ReviewDTO {
		private String userName;
		private String gameName;
		private Integer score;
	public ReviewDTO(String userName, String gameName, Integer score) {
		super();
		this.userName = userName;
		this.gameName = gameName;
		this.score = score;
	}
	public ReviewDTO() {
		super();
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
}
