package game.helper.model;

import java.util.List;

public class User {
	private String username;
	private String password;
	private String name;
	private String lastname;
	private List<Review> reviews;
	
	public User(String username, String password, String name, String lastname, List<Review> reviews) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.reviews = reviews;
	}

	public User() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
}
