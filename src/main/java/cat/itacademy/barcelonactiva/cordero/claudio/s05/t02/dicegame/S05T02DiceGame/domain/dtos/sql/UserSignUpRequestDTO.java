package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql;

public class UserSignUpRequestDTO {
	
	private String email;
	private String nickname;
	private String password;
	
	public UserSignUpRequestDTO() {
		super();
	}

	public UserSignUpRequestDTO(String email, String nickname, String password) {
		super();
		this.email = email;
		this.nickname = nickname;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserSignUpRequestDTO [email=" + email + ", nickname=" + nickname + ", password=" + password + "]";
	}
 
}
 
