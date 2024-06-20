package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.mappers;

import java.util.List;
import java.util.stream.Collectors;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.GameDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.PlayerDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.PlayerSuccessPercentageDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.mongo.Game;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.mongo.Player;

public class MongoMapper {

	
	public static GameDTO mapGameEntityToDto(Game game) {
		
		GameDTO gameDto = new GameDTO();
		gameDto.setDieOne(game.getDieOne());
		gameDto.setDieTwo(game.getDieTwo());
		gameDto.setSumDice(game.getSumDice());
		gameDto.setResult(game.isResult());
		return gameDto;
	}
	
	public static Game mapGameDtoToEntity(GameDTO gameDto) {
		
		Game game = new Game();
		game.setDieOne(gameDto.getDieOne());
		game.setDieTwo(gameDto.getDieTwo());
		game.setSumDice(gameDto.getSumDice());
		game.setResult(gameDto.isResult());
		return game;
	}
	
	public static List<GameDTO> mapListGamesEntityToDto(List<Game> games) {
		
		List<GameDTO> gamesDto = games.stream()
                .map(game -> mapGameEntityToDto(game))
                .collect(Collectors.toList());
	
		return gamesDto;
	}
	
	public static List<Game> mapListGamesDtoToEntity(List<GameDTO> gamesDto) {
		
		List<Game> games = gamesDto.stream()
                .map(gameDto -> mapGameDtoToEntity(gameDto))
                .collect(Collectors.toList());
	
		return games;
	}
	
	public static PlayerDTO mapPlayerEntityToDto(Player player) {
		
		PlayerDTO playerDto = new PlayerDTO();
		playerDto.setUserId(player.getUserId());
		playerDto.setId(player.getId());
		playerDto.setGames(mapListGamesEntityToDto(player.getGames()));
		playerDto.setSuccessPercentage(player.getSuccessPercentage());
		return playerDto;
	}
	
	public static Player mapPlayerDtoToEntity(PlayerDTO playerDto) {
		
		Player player = new Player();
		player.setUserId(playerDto.getUserId());
		player.setId(playerDto.getId());
		player.setGames(mapListGamesDtoToEntity(playerDto.getGames()));
		player.setSuccessPercentage(playerDto.getSuccessPercentage());
		return player;
	}
	
	public static PlayerSuccessPercentageDTO mapPlayerSuccessPercentageEntityToDto(Player player) {
		
		PlayerSuccessPercentageDTO psp = new PlayerSuccessPercentageDTO();
		psp.setUserId(player.getUserId());
		psp.setSuccessPercentage(player.getSuccessPercentage());
		
		return psp;
	}
}
