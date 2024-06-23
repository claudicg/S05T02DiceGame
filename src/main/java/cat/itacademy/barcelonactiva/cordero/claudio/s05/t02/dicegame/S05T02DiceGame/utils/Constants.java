package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils;

public class Constants {
	
	public class Exceptions {
		public static final String USER_NOTFOUND = "User not found";
		public static final String PLAYER_NOTFOUND = "Player not found";
		public static final String INVALID_USER_PARAMS = "Invalid email or password";
		public static final String INVALID_ID = "Invalid id";
	}
	
	public class Messages {
		public static final String INVALID_FORMAT_MAIL = "Invalid mail";
		public static final String INVALID_FORMAT_PASSWORD = "Invalid password";
		public static final String MAIL_FIELD = "Email cannot be empty";
		public static final String WRONG_PASSWORD = "Wrong password";
		public static final String SUCCESSFUL_UPDATE = "Successful update";
		public static final String LIST_GAMES = "List of games";
		public static final String LIST_SUCCESS_PERC_PLAYERS = "List of all players succsess percentages";
		public static final String PLAYER_FOUND = "Player found";
		public static final String DELETED = "Deleted ok";
	}
 
	public class Results {
		public static final int WIN_RESULT = 7;
	}
	
	public class SwaggerAnnotations {
		
		public static final String DELETE_PLAYER = "Delete player.";
		public static final String DELETE_USER = "Delete user.";
		public static final String REGISTER = "Register player."; 
		public static final String ENTRY = "Entry player data.";
		public static final String UPDATE = "Update nickname.";
		public static final String ROLL_DICE = "Play and save a dice roll.";
		public static final String DELETE_GAMES = "Delete a player's games.";
		public static final String PLAYERS_AND_PERCENTAGES = "Show all players and their success percentage.";
		public static final String PLAYER_GAMES = "Show a player's games.";
		public static final String RANKING_PERCENTAGE = "Show  success percentage ranking.";
		public static final String RANKING_LOSER = "Show player with lowest success percentage.";
		public static final String RANKING_WINNER = "Show player with highest success percentage.";
	}
	
	public static final String ANONYMOUS = "ANONYMOUS";
}
