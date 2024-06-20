package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql;


public class JwtAuthenticationResponseDTO {
	
	private String token;
	private String message;
	private String error;
	
	
	public JwtAuthenticationResponseDTO() {
		super();
	}

	public JwtAuthenticationResponseDTO(String token) {
		super();
		this.token = token;
		this.message = "";
		this.error = "";
	}
	

	public JwtAuthenticationResponseDTO(String token, String message, String error) {
		super();
		this.token = token;
		this.message = message;
		this.error = error;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "JwtAuthenticationResponseDTO [token=" + token + ", message=" + message + ", error=" + error + "]";
	}
	
}
 
