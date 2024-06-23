package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.JwtAuthenticationResponseDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserSignInRequestDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserSignUpRequestDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions.InvalidEmailException;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql.AuthenticationService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600) 
@RestController
public class AuthenticationController {
	
	private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	private AuthenticationService authenticationService;
	
	
	@Operation(summary = Constants.SwaggerAnnotations.REGISTER)
	@PostMapping("/dicegame/players/signup")
	public ResponseEntity<JwtAuthenticationResponseDTO> signup(@RequestBody UserSignUpRequestDTO signUpRequestDto) throws InvalidEmailException {
		
		logger.info("AuthenticationController :: signup :: start");
		
		return ResponseEntity.status(HttpStatus.OK).body(authenticationService.signup(signUpRequestDto));
	}
 
	
	@Operation(summary = Constants.SwaggerAnnotations.ENTRY)
	@PostMapping("/dicegame/players/signin")
	public ResponseEntity<JwtAuthenticationResponseDTO> signin(@RequestBody UserSignInRequestDTO signInRequestDto) throws InvalidEmailException {
		
		logger.info("AuthenticationController :: signin :: start");
		
		return ResponseEntity.status(HttpStatus.OK).body(authenticationService.signin(signInRequestDto));
	}
	
}
 
