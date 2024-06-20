package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo;

public class PlayerSuccessPercentageDTO {

	private int userId;
	private int successPercentage;

	public PlayerSuccessPercentageDTO() {
		super();
	}

	public PlayerSuccessPercentageDTO(int userId, int successPercentage) {
		super();
		this.userId = userId;
		this.successPercentage = successPercentage;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSuccessPercentage() {
		return successPercentage;
	}

	public void setSuccessPercentage(int successPercentage) {
		this.successPercentage = successPercentage;
	}

	@Override
	public String toString() {
		return "PlayerSuccessPercentageDTO [userId=" + userId + ", successPercentage=" + successPercentage + "]";
	}

}
