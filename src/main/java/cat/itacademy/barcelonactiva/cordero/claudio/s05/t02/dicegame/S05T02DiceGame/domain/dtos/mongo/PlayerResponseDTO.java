package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo;

public class PlayerResponseDTO {

	private PlayerDTO playerDto;
	private String message;
	private String error;

	public PlayerResponseDTO() {
		super();

	}

	public PlayerResponseDTO(PlayerDTO playerDto, String message, String error) {
		super();
		this.playerDto = playerDto;
		this.message = message;
		this.error = error;
	}

	public PlayerDTO getPlayerDto() {
		return playerDto;
	}

	public void setPlayerDto(PlayerDTO playerDto) {
		this.playerDto = playerDto;
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
		return "PlayerResponseDTO [playerDto=" + playerDto + ", message=" + message + ", error=" + error + "]";
	}

}
