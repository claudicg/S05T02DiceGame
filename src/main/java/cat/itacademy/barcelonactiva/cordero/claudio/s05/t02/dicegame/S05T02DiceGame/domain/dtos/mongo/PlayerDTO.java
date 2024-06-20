package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo;

import java.util.ArrayList;
import java.util.List;

public class PlayerDTO {

	private String id;
	private int userId;
	private List<GameDTO> games;
	private int successPercentage;

	public PlayerDTO() {
		super();
	}

	public PlayerDTO(String id, int userId, int successPercentage) {
		super();
		this.id = id;
		this.userId = userId;
		this.games = new ArrayList<>();
		this.successPercentage = successPercentage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<GameDTO> getGames() {
		return games;
	}

	public void setGames(List<GameDTO> games) {
		this.games = games;
	}

	public int getSuccessPercentage() {
		return successPercentage;
	}

	public void setSuccessPercentage(int successPercentage) {
		this.successPercentage = successPercentage;
	}

	@Override
	public String toString() {
		return "PlayerDTO [id=" + id + ", userId=" + userId + ", games=" + games + ", successPercentage="
				+ successPercentage + "]";
	}

}
