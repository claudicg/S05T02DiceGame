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

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions.InvalidIdException;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql.UserService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.Constants;
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
	public ResponseEntity<UserDTO> update(@PathVariable int userId, @PathVariable String nickname) throws InvalidIdException, NotFoundException {
		
		logger.info("UserController :: update :: update nickname.");
		
		UserDTO userDto = userService.findUser(userId);	
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.update(userDto, nickname));
	}	
	
	
	@Operation(summary = Constants.SwaggerAnnotations.DELETE_USER)
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{userId}/delete")
	public ResponseEntity<Boolean> delete(@PathVariable int userId) throws InvalidIdException, NotFoundException {
		
		logger.info("UserController :: delete :: delete user.");
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.delete(userId));
	}
	
}
