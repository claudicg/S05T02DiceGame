package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mongo.Impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.GameDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.PlayerDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.PlayerSuccessPercentageDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.mongo.Game;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.mongo.Player;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.mappers.MongoMapper;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.repositories.mongo.PlayerRepository;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mongo.PlayerService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mongo.impl.PlayerServiceImpl;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mongo.impl.PlayerServiceUtils;

public class PlayerServiceImpTest {

	@InjectMocks
	private PlayerService playerService = new PlayerServiceImpl();
	
	@Mock
	private PlayerRepository playerRepository;
	
	@Mock
	private PlayerServiceUtils playerServiceUtils; 
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void findPlayerOkTest() {
		
		String id = "6670151f2ac9071f05c98a89";
		
		Game game = new Game();
		game.setDieOne(4);
		game.setDieTwo(3);
		game.setSumDice(7);
		game.setResult(true);
		
		Player player = new Player();
		player.setId(id);
		player.setUserId(1);
		player.setSuccessPercentage(100);
		player.setGames(new ArrayList<>());
		player.getGames().add(game);
		
		when(playerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(player));
		
		PlayerDTO result = playerService.findPlayer(id);
		
		Assertions.assertEquals(id, result.getId());
		Assertions.assertEquals(1, result.getUserId());
		Assertions.assertNotEquals(0, result.getGames().size());
		Assertions.assertEquals(4, result.getGames().get(0).getDieOne());
		Assertions.assertEquals(3, result.getGames().get(0).getDieTwo());
		Assertions.assertEquals(7, result.getGames().get(0).getSumDice());
		Assertions.assertTrue(result.getGames().get(0).isResult());
		Assertions.assertEquals(100, result.getSuccessPercentage());

	}
	
	@Test
	public void rollDiceTest() {
		
		Game game = new Game();
		game.setDieOne(4);
		game.setDieTwo(3);
		game.setSumDice(7);
		game.setResult(true);
		
		when(playerServiceUtils.throwDice()).thenReturn(game);
		
		GameDTO result = playerService.rollDice();
		
		Assertions.assertEquals(4, result.getDieOne());
		Assertions.assertEquals(3, result.getDieTwo());
		Assertions.assertEquals(7, result.getSumDice());
		Assertions.assertTrue(result.isResult());
		
	}
	
	@Test
	public void addGameOkTest() {
		
		GameDTO gameDto1 = new GameDTO();
		gameDto1.setDieOne(4);
		gameDto1.setDieTwo(3);
		gameDto1.setSumDice(7);
		gameDto1.setResult(true);
		
		PlayerDTO playerDto = new PlayerDTO();
		playerDto.setId("6670176a2ac9071f05c98a8a");
		playerDto.setUserId(2);
		playerDto.setSuccessPercentage(100);
		playerDto.setGames(new ArrayList<>());
		playerDto.getGames().add(gameDto1);
		
		Player player = MongoMapper.mapPlayerDtoToEntity(playerDto);
		
		when(playerServiceUtils.recalculateSuccessPercentage(Mockito.any())).thenReturn(player);
		
		when(playerRepository.save(Mockito.any())).thenReturn(player);
		
		GameDTO gameDto2 = new GameDTO();
		gameDto2.setDieOne(5);
		gameDto2.setDieTwo(2);
		gameDto2.setSumDice(7);
		gameDto2.setResult(true);
		
		PlayerDTO result = playerService.addGame(playerDto, gameDto2);
		
		Assertions.assertNotNull(result);
		
	}
	
	@Test
	public void addGameNotOkTest() {
		
		PlayerDTO playerDto = null;
		
		GameDTO gameDto = new GameDTO();
		gameDto.setDieOne(4);
		gameDto.setDieTwo(3);
		gameDto.setSumDice(7);
		gameDto.setResult(true);
		
		PlayerDTO result = playerService.addGame(playerDto, gameDto);
		
		Assertions.assertNull(result);
		
	}
	
	@Test
	public void deleteGamesOkTest() {
	
		String id = "6670151f2ac9071f05c98a89";
		
		Game game = new Game();
		game.setDieOne(4);
		game.setDieTwo(3);
		game.setSumDice(7);
		game.setResult(true);
		
		Player player = new Player();
		player.setId(id);
		player.setUserId(1);
		player.setSuccessPercentage(100);
		player.setGames(new ArrayList<>());
		player.getGames().add(game);
		
		when(playerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(player));
		
		player.setGames(new ArrayList<>());
		player.setSuccessPercentage(0);
		
		when(playerRepository.save(Mockito.any())).thenReturn(player);
		
		PlayerDTO result = playerService.deleteGames(id);
		
		Assertions.assertEquals(id, result.getId());
		Assertions.assertEquals(1, result.getUserId());
		Assertions.assertEquals(0, result.getGames().size());
		Assertions.assertEquals(0, result.getSuccessPercentage());

	}
	
	
	@Test
	public void getPlayerGamesOkTest() throws NotFoundException {
		
		String id = "6670151f2ac9071f05c98a89";
		
		Game game = new Game();
		game.setDieOne(4);
		game.setDieTwo(3);
		game.setSumDice(7);
		game.setResult(true);
		
		Player player = new Player();
		player.setId(id);
		player.setUserId(1);
		player.setSuccessPercentage(100);
		player.setGames(new ArrayList<>());
		player.getGames().add(game);
		
		when(playerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(player));
		
		List<GameDTO> result = playerService.getPlayerGames(id);
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(1, result.size());
		Assertions.assertEquals(4, result.get(0).getDieOne());
		Assertions.assertEquals(3, result.get(0).getDieTwo());
		Assertions.assertEquals(7, result.get(0).getSumDice());
		Assertions.assertTrue(result.get(0).isResult());
			
	}
	
	
	@Test
	public void getPlayersSuccessPercentagesOkTest() {
		
		Game game = new Game();
		game.setDieOne(4);
		game.setDieTwo(3);
		game.setSumDice(7);
		game.setResult(true);
		
		Player player = new Player();
		player.setId("6670151f2ac9071f05c98a89");
		player.setUserId(1);
		player.setSuccessPercentage(100);
		player.setGames(new ArrayList<>());
		player.getGames().add(game);
		
		List<Player> players = new ArrayList<>();
		players.add(player);
		
		when(playerRepository.findAll()).thenReturn(players);
		
		List<PlayerSuccessPercentageDTO> result = playerService.getPlayersSuccessPercentages();
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(1, result.size());
		Assertions.assertEquals(1, result.get(0).getUserId());
		Assertions.assertEquals(100, result.get(0).getSuccessPercentage());
	}
	
	
	@Test
	public void getFirstPlayerInRankingOkTest() {
		
		Game game1 = new Game();
		game1.setDieOne(4);
		game1.setDieTwo(3);
		game1.setSumDice(7);
		game1.setResult(true);
		
		Player player1 = new Player();
		player1.setId("6670151f2ac9071f05c98a89");
		player1.setUserId(1);
		player1.setSuccessPercentage(100);
		player1.setGames(new ArrayList<>());
		player1.getGames().add(game1);
		
		Game game2 = new Game();
		game2.setDieOne(1);
		game2.setDieTwo(3);
		game2.setSumDice(4);
		game2.setResult(false);
		
		Player player2 = new Player();
		player2.setId("6670151f2ac9071f05c98a85");
		player2.setUserId(2);
		player2.setSuccessPercentage(0);
		player2.setGames(new ArrayList<>());
		player2.getGames().add(game2);
		
		List<Player> players = new ArrayList<>();
		players.add(player1);
		players.add(player2);
		
		when(playerRepository.findAll()).thenReturn(players);
		
		PlayerDTO result = playerService.getFirstPlayerInRanking();
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals("6670151f2ac9071f05c98a89", result.getId());
		Assertions.assertEquals(1 , result.getUserId());
		Assertions.assertEquals(100, result.getSuccessPercentage());
		Assertions.assertEquals(1, result.getGames().size());
		Assertions.assertEquals(4, result.getGames().get(0).getDieOne());
		Assertions.assertEquals(3, result.getGames().get(0).getDieTwo());
		Assertions.assertEquals(7, result.getGames().get(0).getSumDice());
		Assertions.assertTrue(result.getGames().get(0).isResult());
	}
	
	@Test
	public void getFirstPlayerInRankingNullTest() {
		
		
		List<Player> players = new ArrayList<>();
		
		when(playerRepository.findAll()).thenReturn(players);
		
		PlayerDTO result = playerService.getFirstPlayerInRanking();
		
		Assertions.assertNull(result);
		
	}
	
	
	@Test
	public void getLastPlayerInRankingOkTest() {
		
		Game game1 = new Game();
		game1.setDieOne(4);
		game1.setDieTwo(3);
		game1.setSumDice(7);
		game1.setResult(true);
		
		Player player1 = new Player();
		player1.setId("6670151f2ac9071f05c98a89");
		player1.setUserId(1);
		player1.setSuccessPercentage(100);
		player1.setGames(new ArrayList<>());
		player1.getGames().add(game1);
		
		Game game2 = new Game();
		game2.setDieOne(1);
		game2.setDieTwo(3);
		game2.setSumDice(4);
		game2.setResult(false);
		
		Player player2 = new Player();
		player2.setId("6670151f2ac9071f05c98a85");
		player2.setUserId(2);
		player2.setSuccessPercentage(0);
		player2.setGames(new ArrayList<>());
		player2.getGames().add(game2);
		
		List<Player> players = new ArrayList<>();
		players.add(player1);
		players.add(player2);
		
		when(playerRepository.findAll()).thenReturn(players);
		
		PlayerDTO result = playerService.getLastPlayerInRanking();
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals("6670151f2ac9071f05c98a85", result.getId());
		Assertions.assertEquals(2 , result.getUserId());
		Assertions.assertEquals(0, result.getSuccessPercentage());
		Assertions.assertEquals(1, result.getGames().size());
		Assertions.assertEquals(1, result.getGames().get(0).getDieOne());
		Assertions.assertEquals(3, result.getGames().get(0).getDieTwo());
		Assertions.assertEquals(4, result.getGames().get(0).getSumDice());
		Assertions.assertFalse(result.getGames().get(0).isResult());	
		
	}
	
	@Test
	public void getLastPlayerInRankingNullTest() {
		
		
		List<Player> players = new ArrayList<>();
		
		when(playerRepository.findAll()).thenReturn(players);
		
		PlayerDTO result = playerService.getLastPlayerInRanking();
		
		Assertions.assertNull(result);
		
	}
	
	
	@Test
	public void deleteTrueTest() {
		
		when(playerRepository.deletePlayer(Mockito.anyInt())).thenReturn(1);
		
		boolean result = playerService.delete(1);
		
		Assertions.assertTrue(result);
	}
	
	@Test
	public void deleteFalseTest() {
		
		when(playerRepository.deletePlayer(Mockito.anyInt())).thenReturn(0);
		
		boolean result = playerService.delete(1);
		
		Assertions.assertFalse(result);
	}
	
}
