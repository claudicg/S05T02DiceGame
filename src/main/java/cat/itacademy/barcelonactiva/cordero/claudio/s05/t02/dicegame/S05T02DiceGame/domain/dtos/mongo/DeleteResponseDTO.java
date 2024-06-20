package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo;

public class DeleteResponseDTO {

	private boolean result;
	private String message;
	private String error;

	public DeleteResponseDTO() {
		super();
	}

	public DeleteResponseDTO(boolean result, String message, String error) {
		super();
		this.result = result;
		this.message = message;
		this.error = error;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
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
		return "DeleteResponseDTO [result=" + result + ", message=" + message + ", error=" + error + "]";
	}

}
