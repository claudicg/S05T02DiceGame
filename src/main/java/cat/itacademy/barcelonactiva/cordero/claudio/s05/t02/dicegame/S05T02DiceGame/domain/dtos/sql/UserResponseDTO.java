package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql;

public class UserResponseDTO {

	private UserDTO userDto;
	private String message;
	private String error;

	public UserResponseDTO() {
		super();
	}

	public UserResponseDTO(UserDTO userDto, String message, String error) {
		super();
		this.userDto = userDto;
		this.message = message;
		this.error = error;
	}

	public UserDTO getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
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
		return "UserResponseDTO [userDto=" + userDto + ", message=" + message + ", error=" + error + "]";
	}

}
