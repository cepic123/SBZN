package game.helper.model.events;

import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import game.helper.model.Review;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("2h30m")
public class ReviewEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	private Review review;
	private Date executionTime;
	public ReviewEvent() {
		super();
		this.executionTime = new Date();
	}

	public ReviewEvent(Review review) {
		super();
		this.review = review;
		this.executionTime = new Date();
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}
}
