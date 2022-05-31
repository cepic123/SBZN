package game.helper.model.dto;

import java.util.List;

import game.helper.model.Game;
import game.helper.model.Review;

public class TLDTO {
	private Game game;
	private List<Review> reviews;
	private double rating;
	private double studioRating;
	private Integer reviewCategory;
	
	public double getStudioRating() {
		return studioRating;
	}

	public void setStudioRating(double studioRating) {
		this.studioRating = studioRating;
	}

	public Integer getReviewCategory() {
		return reviewCategory;
	}

	public void setReviewCategory(Integer reviewCategory) {
		this.reviewCategory = reviewCategory;
	}

	public TLDTO(Game game, List<Review> reviews, double rating) {
		super();
		this.game = game;
		this.reviews = reviews;
		this.rating = rating;
	}

	public TLDTO() {
		super();
		this.rating = 0;
		this.reviewCategory = 0;
		this.studioRating = 0;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public double getGrade() {
        double sum = 0;
        for(Review review : this.game.getReviews()) {
            sum += review.getScore();
        }
        return sum / (double) this.game.getReviews().size();
    }
}
