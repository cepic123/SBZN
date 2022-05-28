package game.helper.model.dto;

public class TopListDTO {
	private String gameName;
	private int points;
	public TopListDTO(String gameName, int points) {
		super();
		this.gameName = gameName;
		this.points = points;
	}
	public TopListDTO() {
		super();
	}
	public TopListDTO(GameResultDTO gameDTO) {
		super();
		this.gameName = gameDTO.getGame().getName();
		this.points = gameDTO.getPoints();
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
}	
