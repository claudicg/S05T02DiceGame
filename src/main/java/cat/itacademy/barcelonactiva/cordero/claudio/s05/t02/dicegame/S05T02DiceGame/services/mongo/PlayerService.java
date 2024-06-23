package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mongo;

import java.util.List;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.GameDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.PlayerDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.PlayerSuccessPercentageDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.mongo.Player;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.sql.User;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions.NotFoundException;

public interface PlayerService {

	Player savePlayerData(Player newPlayer);
	PlayerDTO deleteGames(String id);
	List<GameDTO> getPlayerGames(String id) throws NotFoundException;
	boolean delete(int userId);
	List<PlayerSuccessPercentageDTO> getPlayersSuccessPercentages();
	PlayerDTO getFirstPlayerInRanking();
	PlayerDTO getLastPlayerInRanking();
	PlayerDTO findPlayer(String id);
	GameDTO rollDice();
	PlayerDTO addGame(PlayerDTO playerDto, GameDTO gameDto);
	void saveDataInMongoCollectionPlayer(User user);
	PlayerDTO findPlayerByUserId(int userId);
}
