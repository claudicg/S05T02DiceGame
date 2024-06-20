package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.DeleteResponseDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserResponseDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions.InvalidException;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql.UserService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.Constants;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.DateTimeUtils;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.Validations;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/dicegame/users")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
		
	@Operation(summary = Constants.SwaggerAnnotations.UPDATE) 
	@PutMapping("/{userId}/update/{nickname}")
	public ResponseEntity<UserResponseDTO> update(@PathVariable int userId, @PathVariable String nickname) throws InvalidException {
		
		logger.info("UserController :: update :: update nickname.");
		
		if(!Validations.isValidNumber(userId)) {
			UserResponseDTO response = new UserResponseDTO(null ,"", Constants.Exceptions.INVALID_ID);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		if(!Validations.isValidNickName(nickname)) {
			nickname = Constants.ANONYMOUS;
		}
		
		UserDTO userDto = userService.findUser(userId);
		
		if(userDto == null) {
			UserResponseDTO response = new UserResponseDTO(userDto,"", Constants.Exceptions.USER_NOTFOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}else {
			userDto.setUpdatedAt(DateTimeUtils.parseTimestamp(DateTimeUtils.getCurrentDateTime()));
			userDto.setNickname(nickname);
			UserDTO updatedUserDto = userService.update(userDto);
			
			UserResponseDTO response = new UserResponseDTO(updatedUserDto,Constants.Messages.SUCCESSFUL_UPDATE,"");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}	
	}
	
	@Operation(summary = Constants.SwaggerAnnotations.DELETE_USER)
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{userId}/delete")
	public ResponseEntity<DeleteResponseDTO> delete(@PathVariable int userId) throws InvalidException {
		
		logger.info("UserController :: delete :: delete user.");
		
		if(!Validations.isValidNumber(userId)) {
			DeleteResponseDTO response = new DeleteResponseDTO(false ,"", Constants.Exceptions.INVALID_ID);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		boolean result = userService.delete(userId);
		DeleteResponseDTO response = new DeleteResponseDTO(result, Constants.Messages.DELETED, "");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
}
