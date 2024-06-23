package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions;

public class InvalidEmailException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidEmailException(String message) {
        super(message);
    }
}
