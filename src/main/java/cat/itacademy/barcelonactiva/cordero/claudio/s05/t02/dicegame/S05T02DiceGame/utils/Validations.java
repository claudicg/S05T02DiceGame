package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils;

public class Validations {

	public static boolean isValidEmail(String email) {
		return email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
	}
	
	public static boolean isValidPasswordEightCharacters(String password) {
		return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
	}
	
	public static boolean isValidNumber(int id) {
		return id > 0;
	}
	
	public static boolean isValidNickName(String nickname) {
		
		return nickname != null && !nickname.equals("");
	}
	
	public static boolean isValidMongoId(String id) {
		
		return id.matches("^[a-f0-9]+$");
	}
	
}
