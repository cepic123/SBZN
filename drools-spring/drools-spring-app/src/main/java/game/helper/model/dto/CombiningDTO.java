package game.helper.model.dto;

import game.helper.model.Game;

public class CombiningDTO {
	private Game game;
	private int rankFirstFlow;
	private int rankSecondFlow;
	private double pointsFirstFlow;
	private double pointsSecondFlow;
	private int enumFirstFlow;
	private int enumSecondFlow;
	
	
	public CombiningDTO(Game game, int rankFirstFlow, int rankSecondFlow, double pointsFirstFlow,
			double pointsSecondFlow) {
		super();
		this.game = game;
		this.rankFirstFlow = rankFirstFlow;
		this.rankSecondFlow = rankSecondFlow;
		this.pointsFirstFlow = pointsFirstFlow;
		this.pointsSecondFlow = pointsSecondFlow;
	}
	public CombiningDTO() {
		super();
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public int getRankFirstFlow() {
		return rankFirstFlow;
	}
	public void setRankFirstFlow(int rankFirstFlow) {
		this.rankFirstFlow = rankFirstFlow;
	}
	public int getRankSecondFlow() {
		return rankSecondFlow;
	}
	public void setRankSecondFlow(int rankSecondFlow) {
		this.rankSecondFlow = rankSecondFlow;
	}
	public double getPointsFirstFlow() {
		return pointsFirstFlow;
	}
	public void setPointsFirstFlow(double pointsFirstFlow) {
		this.pointsFirstFlow = pointsFirstFlow;
	}
	public double getPointsSecondFlow() {
		return pointsSecondFlow;
	}
	public void setPointsSecondFlow(double pointsSecondFlow) {
		this.pointsSecondFlow = pointsSecondFlow;
	}
	public int getEnumFirstFlow() {
		return enumFirstFlow;
	}
	public void setEnumFirstFlow(int enumFirstFlow) {
		this.enumFirstFlow = enumFirstFlow;
	}
	public int getEnumSecondFlow() {
		return enumSecondFlow;
	}
	public void setEnumSecondFlow(int enumSecondFlow) {
		this.enumSecondFlow = enumSecondFlow;
	}
	
	
}
