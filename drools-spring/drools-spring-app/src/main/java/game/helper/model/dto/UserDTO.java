package game.helper.model.dto;

import javax.persistence.Column;

public class UserDTO {
	private String username;
	private String password;
	private String name;
	private String lastname;
	public UserDTO(String username, String password, String name, String lastname) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
	}
	public UserDTO() {
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
	
}
