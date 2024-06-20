package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo;

import java.util.ArrayList;
import java.util.List;

public class PlayersSuccessPercentageResponseDTO {

	private List<PlayerSuccessPercentageDTO> successPecentages;
	private String message;
	private String error;

	public PlayersSuccessPercentageResponseDTO() {
		super();
		this.successPecentages = new ArrayList<>();
	}

	public PlayersSuccessPercentageResponseDTO(List<PlayerSuccessPercentageDTO> successPecentages, String message,
			String error) {
		super();
		this.successPecentages = successPecentages;
		this.message = message;
		this.error = error;
	}

	public List<PlayerSuccessPercentageDTO> getSuccessPecentages() {
		return successPecentages;
	}

	public void setSuccessPecentages(List<PlayerSuccessPercentageDTO> successPecentages) {
		this.successPecentages = successPecentages;
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
		return "PlayersSuccessPercentageResponseDTO [successPecentages=" + successPecentages + ", message=" + message
				+ ", error=" + error + "]";
	}

}
