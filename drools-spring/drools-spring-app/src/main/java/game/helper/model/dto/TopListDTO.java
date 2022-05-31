package game.helper.model.dto;

public class TopListDTO {
	private String gameName;
	private double points;
	private int rank;
	
	public TopListDTO(String gameName, double points, int rank) {
		super();
		this.gameName = gameName;
		this.points = points;
		this.rank=rank;
	}
	public TopListDTO() {
		super();
	}
	public TopListDTO(GameResultDTO gameDTO) {
		super();
		this.gameName = gameDTO.getGame().getName();
		this.points = gameDTO.getPoints();
		this.rank = gameDTO.getRank();
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
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
}	
