package game.helper.model.dto;

import game.helper.model.Game;

public class GameResultDTO {
    private Game game;
    private double points;
    private double grade;
    private ParametersDTO parameters;
    private int rank;
    private int upperOrLower;
    private int reviewq;

    public GameResultDTO(Game game, ParametersDTO parameters) {
        super();
        this.game = game;
        this.points = 0;
        this.parameters = parameters;
    }
    
    public GameResultDTO(Game game, double points) {
		super();
		this.game = game;
		this.points = points;
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
    public double getPoints() {
        return points;
    }
    public void setPoints(double points) {
        this.points = points;
    }

    public ParametersDTO getParameters() {
        return parameters;
    }

    public void setParameters(ParametersDTO parameters) {
        this.parameters = parameters;
    }

    public double calculate(double lenght) {
        return (((lenght-0.1))/5+1);
    }

    public double getPriceScaled() {
        return this.getGame().getPrice()/10;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getUpperOrLower() {
        return upperOrLower;
    }

    public void setUpperOrLower(int upperOrLower) {
        this.upperOrLower = upperOrLower;
    }

    public int getReviewq() {
        return reviewq;
    }

    public void setReviewq(int reviewq) {
        this.reviewq = reviewq;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

}