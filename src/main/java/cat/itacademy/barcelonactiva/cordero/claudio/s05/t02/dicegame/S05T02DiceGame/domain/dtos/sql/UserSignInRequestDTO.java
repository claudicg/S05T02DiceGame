package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql;


public class UserSignInRequestDTO {
	
	private String email;
	private String password;
	
	public UserSignInRequestDTO() {
		super();
	}
 
	public UserSignInRequestDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
 
	public String getEmail() {
		return email;
	}
 
	public void setEmail(String email) {
		this.email = email;
	}
 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
 
	@Override
	public String toString() {
		return "SignInRequestDto [email=" + email + ", password=" + password + "]";
	}
 
}
 
