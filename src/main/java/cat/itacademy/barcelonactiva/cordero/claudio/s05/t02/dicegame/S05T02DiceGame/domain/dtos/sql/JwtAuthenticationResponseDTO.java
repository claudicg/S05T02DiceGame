package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql;


public class JwtAuthenticationResponseDTO {
	
	private String token;
	
	public JwtAuthenticationResponseDTO(String token) {
		super();
		this.token = token;
	}

	public JwtAuthenticationResponseDTO() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "JwtAuthenticationResponseDTO [token=" + token + "]";
	}	
}
 
