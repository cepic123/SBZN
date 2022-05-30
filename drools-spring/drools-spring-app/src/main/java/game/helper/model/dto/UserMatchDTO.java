package game.helper.model.dto;

import java.util.List;

import game.helper.model.Review;
import game.helper.model.User;
import game.helper.model.enums.MatchCoeficient;

public class UserMatchDTO {
	private User user;
	private User otherUser;
	private List<Review> reviews;
	private Integer no_matches;
	private MatchCoeficient matchCoeficient;
	
	public UserMatchDTO(User user, User otherUser, List<Review> reviews, Integer no_matches, MatchCoeficient matchCoeficient) {
		super();
		this.user = user;
		this.otherUser = otherUser;
		this.reviews = reviews;
		this.no_matches = no_matches;
		this.matchCoeficient = matchCoeficient;
	}

	public MatchCoeficient getMatchCoeficient() {
		return matchCoeficient;
	}

	public void setMatchCoeficient(MatchCoeficient matchCoeficient) {
		this.matchCoeficient = matchCoeficient;
	}

	public UserMatchDTO() {
		super();
		this.no_matches = 0;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getNo_matches() {
		return no_matches;
	}

	public void setNo_matches(Integer no_matches) {
		this.no_matches = no_matches;
	}

	public User getOtherUser() {
		return otherUser;
	}

	public void setOtherUser(User otherUser) {
		this.otherUser = otherUser;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
}
