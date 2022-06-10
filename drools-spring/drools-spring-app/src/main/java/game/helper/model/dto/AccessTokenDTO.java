package game.helper.model.dto;

public class AccessTokenDTO {
	private String accessToken;

	public AccessTokenDTO(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	public AccessTokenDTO() {
		super();
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
}
