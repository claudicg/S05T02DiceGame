package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mongo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.GameDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.PlayerDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.PlayerSuccessPercentageDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.mongo.Game;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.mongo.Player;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.sql.User;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.mappers.MongoMapper;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.repositories.mongo.PlayerRepository;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mongo.PlayerService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.Constants;


@Service("PlayerService")
public class PlayerServiceImpl implements PlayerService{
	
	private static Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private PlayerServiceUtils playerServiceUtils;

	
	@Override
	public Player savePlayerData(Player newPlayer) {
		
		logger.info("PlayerServiceImpl :: savePlayerData :: save player in Mongo.");
		return playerRepository.save(newPlayer);	
	}
	
	@Override
	public PlayerDTO findPlayer(String id) {
		
		logger.info("PlayerServiceImpl :: findPlayer :: find the player.");
		
		Player player = playerRepository.findById(id).orElse(null);
		
		if(player != null) {
			return MongoMapper.mapPlayerEntityToDto(player);
		} else {
			return null;
		}	
		
	}
	
	@Override
	public PlayerDTO findPlayerByUserId(int userId) {
		
		logger.info("PlayerServiceImpl :: findPlayerByUserId :: find the player.");
		
		Player player = playerRepository.findByUserId(userId);
		
		if(player != null) {
			return MongoMapper.mapPlayerEntityToDto(player);
		} else {
			return null;
		}	
		
	}
	
	@Override
	public GameDTO rollDice() {
		
		logger.info("PlayerServiceImpl :: rollDice :: roll the dice.");		

		Game game = playerServiceUtils.throwDice();
		
		return MongoMapper.mapGameEntityToDto(game);
		
	}

	@Override
	public PlayerDTO addGame(PlayerDTO playerDto, GameDTO gameDto) {
		
		if(playerDto != null && gameDto != null) {
			Player player = MongoMapper.mapPlayerDtoToEntity(playerDto);
			List<Game> games = player.getGames();
			games.add(MongoMapper.mapGameDtoToEntity(gameDto));
			player = playerServiceUtils.recalculateSuccessPercentage(player);
			player = playerRepository.save(player);
			return MongoMapper.mapPlayerEntityToDto(player);
		} else {
			return null;
		} 
		
	}

	
	@Override
	public PlayerDTO deleteGames(String id) {
		
		logger.info("PlayerServiceImpl :: deleteGames :: delete list of games.");
		Player player = playerRepository.findById(id).orElse(null);
		if(player != null) {
			player.setGames(new ArrayList<>());
			player.setSuccessPercentage(0);
			return MongoMapper.mapPlayerEntityToDto(playerRepository.save(player));
		}else {
			return null;
		}	
	}
	
	@Override
	public List<GameDTO> getPlayerGames(String id) throws NotFoundException {
		
		logger.info("PlayerServiceImpl :: getPlayerGames :: Show a player's games.");
		
		Player player = playerRepository.findById(id).orElse(null);
		if(player != null) {
			
			List<Game> games = player.getGames();
			return MongoMapper.mapListGamesEntityToDto(games);
		}else {
			throw new NotFoundException(Constants.Exceptions.PLAYER_NOTFOUND);
		}	
		
	}

	
	@Override
	public List<PlayerSuccessPercentageDTO> getPlayersSuccessPercentages() {
		
		logger.info("PlayerServiceImpl :: getPlayersSuccessPercentages :: Show a player and success percetages list.");
		
		List<Player> players = playerRepository.findAll();
		return players.stream()
                .map(player -> MongoMapper.mapPlayerSuccessPercentageEntityToDto(player))
                .collect(Collectors.toList());
	}
	

	@Override
	public PlayerDTO getFirstPlayerInRanking() {
		
		logger.info("PlayerServiceImpl :: getFirstPlayerInRanking :: Show first player in ranking.");
		
		List<PlayerDTO> players = getPlayers();
		if(players.isEmpty()) {
			return null;
		}else {
			return players.get(0);
		}	
	}


	@Override
	public PlayerDTO getLastPlayerInRanking() {
		
		logger.info("PlayerServiceImpl :: getLastPlayerInRanking :: Show last player in ranking.");		
		
		List<PlayerDTO> players = getPlayers();
		if(players.isEmpty()) {
			return null;
		}else {
			return players.get(players.size() - 1);
		}		
		
	}
	
	@Override
	public boolean delete(int userId) {
		
		int deletedRecords = playerRepository.deletePlayer(userId);
		return deletedRecords > 0 ? true : false;
		
	}
	
	

	
	public List<PlayerDTO> getPlayers() {
		
		List<Player> players = playerRepository.findAll();
		players.sort((p1, p2) -> Double.compare(p2.getSuccessPercentage(), p1.getSuccessPercentage()));
		
		List<PlayerDTO> playersDto = players.stream()
                .map(item -> MongoMapper.mapPlayerEntityToDto(item))
                .collect(Collectors.toList()); 
		
		return playersDto;
	}
	
	public void saveDataInMongoCollectionPlayer(User user) {
		
		int userId = user.getUserId();
		Player player = new Player(userId);
		savePlayerData(player);
	}	
	
}
