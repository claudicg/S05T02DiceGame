package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.mongo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "players")
public class Player {

	@Id
	private String id;
	
	private int userId;
	private int successPercentage;
	private List<Game> games = new ArrayList<>();

	public Player() {
		super();
	}

	public Player(int userId) {
		super();
		this.userId = userId;
	}
	
	
	public Player(int userId, int successPercentage, List<Game> games) {
		super();
		this.successPercentage = successPercentage;
		this.games = games;
		this.userId = userId;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public int getSuccessPercentage() {
		return successPercentage;
	}

	public void setSuccessPercentage(int successPercentage) {
		this.successPercentage = successPercentage;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", userId=" + userId + ", successPercentage=" + successPercentage + ", games="
				+ games + "]";
	}

}
