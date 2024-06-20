package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo;

import java.util.ArrayList;
import java.util.List;

public class PlayerGamesResponseDTO {

	private List<GameDTO> games;
	private String message;
	private String error;

	public PlayerGamesResponseDTO() {
		super();
		this.games = new ArrayList<>();
	}

	public PlayerGamesResponseDTO(List<GameDTO> games, String message, String error) {
		super();
		this.games = games;
		this.message = message;
		this.error = error;
	}

	public List<GameDTO> getGames() {
		return games;
	}

	public void setGames(List<GameDTO> games) {
		this.games = games;
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
		return "PlayerGamesResponseDTO [games=" + games + ", message=" + message + ", error=" + error + "]";
	}

}
