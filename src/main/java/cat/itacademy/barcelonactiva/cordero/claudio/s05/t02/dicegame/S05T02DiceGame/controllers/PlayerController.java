package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.DeleteResponseDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.GameDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.PlayerDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.PlayerGamesResponseDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.PlayerResponseDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.PlayerSuccessPercentageDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.PlayersSuccessPercentageResponseDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions.InvalidException;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mongo.PlayerService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.Constants;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.Validations;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/dicegame/players")
public class PlayerController { 
	 
	private static Logger logger = LoggerFactory.getLogger(PlayerController.class);
	
	@Autowired
	private PlayerService playerService;
	
	
	@Operation(summary = Constants.SwaggerAnnotations.ROLL_DICE)
	@GetMapping("/{id}/games/play")
	public ResponseEntity<PlayerDTO> play(@PathVariable String id) throws NotFoundException {
		
		logger.info("PlayerController :: play :: play and save a game.");
		
		PlayerDTO playerDto = playerService.findPlayer(id);
		
		if(playerDto != null) {
			
			GameDTO gameDto = playerService.rollDice();
			PlayerDTO updatedPlayer = playerService.addGame(playerDto, gameDto);
			if(updatedPlayer != null) {
				return ResponseEntity.status(HttpStatus.OK).body(updatedPlayer);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(updatedPlayer);
			}
			
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

	}

	@Operation(summary = Constants.SwaggerAnnotations.DELETE_GAMES)
	@PutMapping("/{id}/games/delete")
	public ResponseEntity<PlayerResponseDTO> deleteGames(@PathVariable String id) {
		
		logger.info("PlayerController :: deleteGames :: clears a player's game list.");
		
		if(!Validations.isValidMongoId(id)) {
			PlayerResponseDTO response = new PlayerResponseDTO(null ,"", Constants.Exceptions.INVALID_ID);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		PlayerDTO playerDto = playerService.deleteGames(id);
		
		if(playerDto == null) {
			PlayerResponseDTO response = new PlayerResponseDTO(playerDto,"", Constants.Exceptions.PLAYER_NOTFOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}else {
			PlayerResponseDTO response = new PlayerResponseDTO(playerDto,Constants.Messages.SUCCESSFUL_UPDATE,"");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}	

	}
	
	@Operation(summary = Constants.SwaggerAnnotations.PLAYER_GAMES)
	@GetMapping("/{id}/games")
	public ResponseEntity<PlayerGamesResponseDTO> getPlayerGames(@PathVariable String id) throws InvalidException, NotFoundException{
		
		logger.info("PlayerController :: showOnePlayersGames :: show a player's games.");
		
		if(!Validations.isValidMongoId(id)) {
			PlayerGamesResponseDTO response = new PlayerGamesResponseDTO(null ,"", Constants.Exceptions.INVALID_ID);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		List<GameDTO> games= playerService.getPlayerGames(id);
		
		PlayerGamesResponseDTO response = new PlayerGamesResponseDTO(games, Constants.Messages.LIST_GAMES, "");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@Operation(summary = Constants.SwaggerAnnotations.PLAYERS_AND_PERCENTAGES)
	@GetMapping("/succsessPercentages")
	public ResponseEntity<PlayersSuccessPercentageResponseDTO> getPlayersSuccessPercentages(){
		
		logger.info("PlayerController :: getPlayersSuccessPercentages :: list all players and their success percentage.");
		
		List<PlayerSuccessPercentageDTO> list = playerService.getPlayersSuccessPercentages();
		PlayersSuccessPercentageResponseDTO response = new PlayersSuccessPercentageResponseDTO(list, Constants.Messages.LIST_SUCCESS_PERC_PLAYERS, "");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	@Operation(summary = Constants.SwaggerAnnotations.RANKING_PERCENTAGE) 
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/ranking/averageSuccessPercentage")
	public double getAverageSuccessPercentage() {
		
		logger.info("PlayerController :: getAverageSuccessPercentage :: average succsses percentage.");
		return calculateAverageSuccsessPercentage();
	}
	
	
	@Operation(summary = Constants.SwaggerAnnotations.RANKING_LOSER)
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/ranking/loser")
	public ResponseEntity<PlayerResponseDTO> getLastPlayerInRanking() {
		
		logger.info("PlayerController :: getLastPlayerInRanking :: show last player in ranking.");
		
		PlayerDTO playerDto = playerService.getLastPlayerInRanking();
		
		if(playerDto == null) {
			PlayerResponseDTO response = new PlayerResponseDTO(playerDto, "", Constants.Exceptions.PLAYER_NOTFOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}else{
			PlayerResponseDTO response = new PlayerResponseDTO(playerDto,Constants.Messages.PLAYER_FOUND,"");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} 
	}
	
	
	@Operation(summary = Constants.SwaggerAnnotations.RANKING_WINNER)
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/ranking/winner")
	public ResponseEntity<PlayerResponseDTO> getFirstPlayerInRanking() {
		
		logger.info("PlayerController :: getFirstPlayerInRanking :: get first player in ranking.");
		
		PlayerDTO playerDto = playerService.getFirstPlayerInRanking();
		
		if(playerDto == null) {
			PlayerResponseDTO response = new PlayerResponseDTO(playerDto, "", Constants.Exceptions.PLAYER_NOTFOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}else{
			PlayerResponseDTO response = new PlayerResponseDTO(playerDto, Constants.Messages.PLAYER_FOUND, "");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}
	
	
	@Operation(summary = Constants.SwaggerAnnotations.DELETE_PLAYER)
	@DeleteMapping("/{userId}/deletePlayer")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<DeleteResponseDTO> deletePlayer(@PathVariable int userId) throws InvalidException {
		
		logger.info("PlayerController :: deletePlayer :: delete player.");
		
		if(!Validations.isValidNumber(userId)) {
			DeleteResponseDTO response = new DeleteResponseDTO(false, "", Constants.Exceptions.INVALID_ID);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		boolean result = playerService.delete(userId);
		DeleteResponseDTO response = new DeleteResponseDTO(result, Constants.Messages.DELETED, "");	
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	public double calculateAverageSuccsessPercentage() {
		
		List<PlayerSuccessPercentageDTO> list = playerService.getPlayersSuccessPercentages();
		
		int sum = 0;
		for(PlayerSuccessPercentageDTO dto : list) {
			sum = sum + dto.getSuccessPercentage();
		}
		
		return sum / list.size();
	
	}
	 	 	 
}
