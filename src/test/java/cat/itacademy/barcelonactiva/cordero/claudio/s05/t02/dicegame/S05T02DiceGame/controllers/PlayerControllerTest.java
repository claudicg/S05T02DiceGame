package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.controllers;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.DeleteResponseDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.GameDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.PlayerDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.PlayerGamesResponseDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.PlayerResponseDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.PlayerSuccessPercentageDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.PlayersSuccessPercentageResponseDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions.InvalidIdException;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mongo.PlayerService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.Constants;

public class PlayerControllerTest {
	
	@InjectMocks
	private PlayerController playerController;
	
	@Mock
	private PlayerService playerService;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void playOkTest() throws NotFoundException {
		
		String id = "6670151f2ac9071f05c98a89";
		
		GameDTO gameDto = new GameDTO();
		gameDto.setDieOne(5);
		gameDto.setDieTwo(2);
		gameDto.setSumDice(7);
		gameDto.setResult(true);
		
		PlayerDTO playerDto = new PlayerDTO();
		playerDto.setId(id);
		playerDto.setUserId(1);
		playerDto.setGames(new ArrayList<>());
		playerDto.getGames().add(gameDto);
		playerDto.setSuccessPercentage(100);
		
		when(playerService.findPlayer(Mockito.anyString())).thenReturn(playerDto);
		
		GameDTO gameDto2 = new GameDTO();
		gameDto2.setDieOne(4);
		gameDto2.setDieTwo(3);
		gameDto2.setSumDice(7);
		gameDto2.setResult(true);
		
		when(playerService.rollDice()).thenReturn(gameDto2);
		
		playerDto.getGames().add(gameDto2);
		
		when(playerService.addGame(Mockito.any(), Mockito.any())).thenReturn(playerDto);
		
		ResponseEntity<PlayerDTO> result = playerController.play(id);
		
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertNotNull(result.getBody());
		Assertions.assertEquals(id, result.getBody().getId());
		Assertions.assertEquals(1, result.getBody().getUserId());
		Assertions.assertNotNull(result.getBody().getGames());
		Assertions.assertNotEquals(0, result.getBody().getGames().size());
		Assertions.assertEquals(5, result.getBody().getGames().get(0).getDieOne());
		Assertions.assertEquals(2, result.getBody().getGames().get(0).getDieTwo());
		Assertions.assertEquals(7, result.getBody().getGames().get(0).getSumDice());
		Assertions.assertTrue(result.getBody().getGames().get(0).isResult());
		Assertions.assertEquals(4, result.getBody().getGames().get(1).getDieOne());
		Assertions.assertEquals(3, result.getBody().getGames().get(1).getDieTwo());
		Assertions.assertEquals(7, result.getBody().getGames().get(1).getSumDice());
		Assertions.assertTrue(result.getBody().getGames().get(1).isResult());
		Assertions.assertEquals(100, result.getBody().getSuccessPercentage());

	}
	
	@Test
	public void deleteInvalidMongoIdTest() {
		
		String id = "6670151f2ac9071f05c98a8x";
		
		ResponseEntity<PlayerResponseDTO> result = playerController.deleteGames(id);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		Assertions.assertNull(result.getBody().getPlayerDto());
		Assertions.assertEquals("", result.getBody().getMessage());
		Assertions.assertEquals(Constants.Exceptions.INVALID_ID, result.getBody().getError());
		
	}
	
	@Test
	public void deleteNotFoundTest() {
		
		String id = "6670151f2ac9071f05c98a89";
		
		when(playerService.deleteGames(Mockito.anyString())).thenReturn(null);
		
		ResponseEntity<PlayerResponseDTO> result = playerController.deleteGames(id);
		
		Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
		Assertions.assertNull(result.getBody().getPlayerDto());
		Assertions.assertEquals("", result.getBody().getMessage());
		Assertions.assertEquals(Constants.Exceptions.PLAYER_NOTFOUND, result.getBody().getError());
		
	}
	
	@Test
	public void deleteOkTest() {
		
		String id = "6670151f2ac9071f05c98a89";

		PlayerDTO playerDto = new PlayerDTO();
		playerDto.setId(id);
		playerDto.setUserId(1);
		playerDto.setGames(new ArrayList<>());
		playerDto.setSuccessPercentage(0);
		
		when(playerService.deleteGames(Mockito.anyString())).thenReturn(playerDto);
		
		ResponseEntity<PlayerResponseDTO> result = playerController.deleteGames(id);
		
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertEquals(id, result.getBody().getPlayerDto().getId());
		Assertions.assertEquals(1, result.getBody().getPlayerDto().getUserId());
		Assertions.assertNotNull(result.getBody().getPlayerDto().getGames());
		Assertions.assertEquals(0, result.getBody().getPlayerDto().getGames().size());
		Assertions.assertEquals(0, result.getBody().getPlayerDto().getSuccessPercentage());
		Assertions.assertEquals(Constants.Messages.SUCCESSFUL_UPDATE, result.getBody().getMessage());
		Assertions.assertEquals("", result.getBody().getError());
		
	}
	
	@Test
	public void getPlayerGamesInvalidMongoIdTest() throws InvalidIdException, NotFoundException {
		
		String id = "6670151f2ac9071f05c98a8x";
		
		ResponseEntity<PlayerGamesResponseDTO> result = playerController.getPlayerGames(id);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		Assertions.assertNull(result.getBody().getGames());
		Assertions.assertEquals("", result.getBody().getMessage());
		Assertions.assertEquals(Constants.Exceptions.INVALID_ID, result.getBody().getError());
	
	}
	
	@Test
	public void getPlayerGamesOkTest() throws NotFoundException, InvalidIdException {
		
		String id = "6670151f2ac9071f05c98a89";
		
		GameDTO gameDto = new GameDTO();
		gameDto.setDieOne(5);
		gameDto.setDieTwo(2);
		gameDto.setSumDice(7);
		gameDto.setResult(true);
		
		List<GameDTO> games = new ArrayList<>();
		games.add(gameDto);
		
		when(playerService.getPlayerGames(Mockito.anyString())).thenReturn(games);
		
		ResponseEntity<PlayerGamesResponseDTO> result = playerController.getPlayerGames(id);
		
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertNotNull(result.getBody().getGames());
		Assertions.assertNotEquals(0, result.getBody().getGames().size());
		Assertions.assertEquals(5, result.getBody().getGames().get(0).getDieOne());
		Assertions.assertEquals(2, result.getBody().getGames().get(0).getDieTwo());
		Assertions.assertEquals(7, result.getBody().getGames().get(0).getSumDice());
		Assertions.assertTrue(result.getBody().getGames().get(0).isResult());
		Assertions.assertEquals(Constants.Messages.LIST_GAMES, result.getBody().getMessage());
		Assertions.assertEquals("", result.getBody().getError());
		
	}
	
	@Test
	public void getPlayersSuccessPercentagesTest() {
		
		List<PlayerSuccessPercentageDTO> list = new ArrayList<>();
		
		PlayerSuccessPercentageDTO playerDto1 = new PlayerSuccessPercentageDTO();
		playerDto1.setUserId(1);
		playerDto1.setSuccessPercentage(23);
		list.add(playerDto1);
		
		PlayerSuccessPercentageDTO playerDto2 = new PlayerSuccessPercentageDTO();
		playerDto2.setUserId(2);
		playerDto2.setSuccessPercentage(16);
		list.add(playerDto2);
		
		PlayerSuccessPercentageDTO playerDto3 = new PlayerSuccessPercentageDTO();
		playerDto3.setUserId(3);
		playerDto3.setSuccessPercentage(31);
		list.add(playerDto3);
		
		when(playerService.getPlayersSuccessPercentages()).thenReturn(list);
		
		ResponseEntity<PlayersSuccessPercentageResponseDTO> result = playerController.getPlayersSuccessPercentages();
		
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertNotNull(result.getBody().getSuccessPecentages());
		Assertions.assertNotEquals(0, result.getBody().getSuccessPecentages().size());
		Assertions.assertEquals(1, result.getBody().getSuccessPecentages().get(0).getUserId());
		Assertions.assertEquals(23, result.getBody().getSuccessPecentages().get(0).getSuccessPercentage());
		Assertions.assertEquals(2, result.getBody().getSuccessPecentages().get(1).getUserId());
		Assertions.assertEquals(16, result.getBody().getSuccessPecentages().get(1).getSuccessPercentage());
		Assertions.assertEquals(3, result.getBody().getSuccessPecentages().get(2).getUserId());
		Assertions.assertEquals(31, result.getBody().getSuccessPecentages().get(2).getSuccessPercentage());
		Assertions.assertEquals(Constants.Messages.LIST_SUCCESS_PERC_PLAYERS, result.getBody().getMessage());
		Assertions.assertEquals("", result.getBody().getError());
		
	}
	
	@Test
	public void getAverageSuccessPercentageTest() {
		
		List<PlayerSuccessPercentageDTO> list = new ArrayList<>();
		
		PlayerSuccessPercentageDTO playerDto1 = new PlayerSuccessPercentageDTO();
		playerDto1.setUserId(1);
		playerDto1.setSuccessPercentage(23);
		list.add(playerDto1);
		
		PlayerSuccessPercentageDTO playerDto2 = new PlayerSuccessPercentageDTO();
		playerDto2.setUserId(2);
		playerDto2.setSuccessPercentage(16);
		list.add(playerDto2);
		
		PlayerSuccessPercentageDTO playerDto3 = new PlayerSuccessPercentageDTO();
		playerDto3.setUserId(3);
		playerDto3.setSuccessPercentage(31);
		list.add(playerDto3);
		
		double average = (playerDto1.getSuccessPercentage() + playerDto2.getSuccessPercentage() + playerDto3.getSuccessPercentage()) / list.size();
		
		when(playerService.getPlayersSuccessPercentages()).thenReturn(list);
		
		double result = playerController.calculateAverageSuccsessPercentage();
		
		Assertions.assertEquals(average, result);
		
	}
	
	@Test
	public void getLastPlayerInRankingNotFoundTest() {
		
		when(playerService.getLastPlayerInRanking()).thenReturn(null);	
		
		ResponseEntity<PlayerResponseDTO> result = playerController.getLastPlayerInRanking();
	
		Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
		Assertions.assertNull(result.getBody().getPlayerDto());
		Assertions.assertEquals("", result.getBody().getMessage());
		Assertions.assertEquals(Constants.Exceptions.PLAYER_NOTFOUND, result.getBody().getError());
		
	}
	
	@Test
	public void getLastPlayerInRankingOkTest() {
		
		GameDTO gameDto = new GameDTO();
		gameDto.setDieOne(5);
		gameDto.setDieTwo(1);
		gameDto.setSumDice(6);
		gameDto.setResult(false);
		
		PlayerDTO playerDto = new PlayerDTO();
		playerDto.setId("6670151f2ac9071f05c98a89");
		playerDto.setUserId(1);
		playerDto.setGames(new ArrayList<>());
		playerDto.getGames().add(gameDto);
		playerDto.setSuccessPercentage(0);
		
		when(playerService.getLastPlayerInRanking()).thenReturn(playerDto);	
		
		ResponseEntity<PlayerResponseDTO> result = playerController.getLastPlayerInRanking();
	
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertEquals("6670151f2ac9071f05c98a89", result.getBody().getPlayerDto().getId());
		Assertions.assertEquals(1, result.getBody().getPlayerDto().getUserId());
		Assertions.assertNotNull(result.getBody().getPlayerDto().getGames());
		Assertions.assertNotEquals(0, result.getBody().getPlayerDto().getGames().size());
		Assertions.assertEquals(5, result.getBody().getPlayerDto().getGames().get(0).getDieOne());
		Assertions.assertEquals(1, result.getBody().getPlayerDto().getGames().get(0).getDieTwo());
		Assertions.assertEquals(6, result.getBody().getPlayerDto().getGames().get(0).getSumDice());
		Assertions.assertFalse(result.getBody().getPlayerDto().getGames().get(0).isResult());
		Assertions.assertEquals(0, result.getBody().getPlayerDto().getSuccessPercentage());
		Assertions.assertEquals(Constants.Messages.PLAYER_FOUND, result.getBody().getMessage());
		Assertions.assertEquals("", result.getBody().getError());
		
	}
	
	@Test
	public void getFirstPlayerInRankingNotFoundTest() {
		
		when(playerService.getFirstPlayerInRanking()).thenReturn(null);	
		
		ResponseEntity<PlayerResponseDTO> result = playerController.getFirstPlayerInRanking();
	
		Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
		Assertions.assertNull(result.getBody().getPlayerDto());
		Assertions.assertEquals("", result.getBody().getMessage());
		Assertions.assertEquals(Constants.Exceptions.PLAYER_NOTFOUND, result.getBody().getError());
		
	}
	
	@Test
	public void getFirstPlayerInRankingOkTest() {
		
		GameDTO gameDto = new GameDTO();
		gameDto.setDieOne(5);
		gameDto.setDieTwo(2);
		gameDto.setSumDice(7);
		gameDto.setResult(true);
		
		PlayerDTO playerDto = new PlayerDTO();
		playerDto.setId("6670151f2ac9071f05c98a89");
		playerDto.setUserId(1);
		playerDto.setGames(new ArrayList<>());
		playerDto.getGames().add(gameDto);
		playerDto.setSuccessPercentage(100);
		
		when(playerService.getFirstPlayerInRanking()).thenReturn(playerDto);	
		
		ResponseEntity<PlayerResponseDTO> result = playerController.getFirstPlayerInRanking();
	
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertEquals("6670151f2ac9071f05c98a89", result.getBody().getPlayerDto().getId());
		Assertions.assertEquals(1, result.getBody().getPlayerDto().getUserId());
		Assertions.assertNotNull(result.getBody().getPlayerDto().getGames());
		Assertions.assertNotEquals(0, result.getBody().getPlayerDto().getGames().size());
		Assertions.assertEquals(5, result.getBody().getPlayerDto().getGames().get(0).getDieOne());
		Assertions.assertEquals(2, result.getBody().getPlayerDto().getGames().get(0).getDieTwo());
		Assertions.assertEquals(7, result.getBody().getPlayerDto().getGames().get(0).getSumDice());
		Assertions.assertTrue(result.getBody().getPlayerDto().getGames().get(0).isResult());
		Assertions.assertEquals(100, result.getBody().getPlayerDto().getSuccessPercentage());
		Assertions.assertEquals(Constants.Messages.PLAYER_FOUND, result.getBody().getMessage());
		Assertions.assertEquals("", result.getBody().getError());
		
	}
	
	@Test
	public void deletePlayerInvalidUserIdTest() throws InvalidIdException {
		
		int userId = 0;
		
		ResponseEntity<DeleteResponseDTO> result = playerController.deletePlayer(userId);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		Assertions.assertFalse(result.getBody().isResult());
		Assertions.assertEquals("", result.getBody().getMessage());
		Assertions.assertEquals(Constants.Exceptions.INVALID_ID, result.getBody().getError());
	
	}
	
	@Test
	public void deletePlayerOkTest() throws InvalidIdException {
		
		int userId = 1;
		
		GameDTO gameDto = new GameDTO();
		gameDto.setDieOne(5);
		gameDto.setDieTwo(1);
		gameDto.setSumDice(6);
		gameDto.setResult(false);
		
		PlayerDTO playerDto = new PlayerDTO();
		playerDto.setId("6670151f2ac9071f05c98a89");
		playerDto.setUserId(1);
		playerDto.setGames(new ArrayList<>());
		playerDto.getGames().add(gameDto);
		playerDto.setSuccessPercentage(0);
		
		when(playerService.findPlayerByUserId(Mockito.anyInt())).thenReturn(playerDto);
		
		when(playerService.delete(Mockito.anyInt())).thenReturn(true);
		
		ResponseEntity<DeleteResponseDTO> result = playerController.deletePlayer(userId);
		
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertTrue(result.getBody().isResult());
		Assertions.assertEquals(Constants.Messages.DELETED, result.getBody().getMessage());
		Assertions.assertEquals("", result.getBody().getError());
		
	}

}
