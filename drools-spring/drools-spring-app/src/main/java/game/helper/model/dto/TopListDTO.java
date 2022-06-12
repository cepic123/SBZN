package game.helper.model.dto;

public class TopListDTO {
	private String gameName;
	private double points;
	
	public TopListDTO(String gameName, double points) {
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
	public TopListDTO(CombiningDTO gameDTO) {
		super();
		this.gameName = gameDTO.getGame().getName();
		this.points = (gameDTO.getPointsFirstFlow()+gameDTO.getPointsSecondFlow())/2;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public double getPoints() {
		return points;
	}
	public void setPoints(double points) {
		this.points = points;
	}
}	
