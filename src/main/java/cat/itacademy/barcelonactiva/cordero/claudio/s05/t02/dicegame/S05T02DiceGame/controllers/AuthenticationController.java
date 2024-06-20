package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.JwtAuthenticationResponseDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserSignInRequestDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserSignUpRequestDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql.AuthenticationService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.Constants;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.Validations;
import io.swagger.v3.oas.annotations.Operation;
 
@RestController
public class AuthenticationController {
	
	private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	private AuthenticationService authenticationService;
	
	
	@Operation(summary = Constants.SwaggerAnnotations.REGISTER)
	@PostMapping("/dicegame/players/signup")
	public ResponseEntity<JwtAuthenticationResponseDTO> signup(@RequestBody UserSignUpRequestDTO signUpRequestDto) {
		
		logger.info("AuthenticationController :: signup :: start");
		
		if(!Validations.isValidEmail(signUpRequestDto.getEmail().trim())) {
			JwtAuthenticationResponseDTO response = new JwtAuthenticationResponseDTO( "", "", Constants.Messages.INVALID_FORMAT_MAIL);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		JwtAuthenticationResponseDTO response = authenticationService.signup(signUpRequestDto);
		response.setMessage(Constants.Messages.SUCCESSFUL_SIGNUP);
		response.setError("");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
 
	
	@Operation(summary = Constants.SwaggerAnnotations.ENTRY)
	@PostMapping("/dicegame/players/signin")
	public ResponseEntity<JwtAuthenticationResponseDTO> signin(@RequestBody UserSignInRequestDTO signInRequestDto) {
		
		logger.info("AuthenticationController :: signin :: start");
		
		if(!Validations.isValidEmail(signInRequestDto.getEmail().trim())) {
			JwtAuthenticationResponseDTO response = new JwtAuthenticationResponseDTO( "", "", Constants.Messages.INVALID_FORMAT_MAIL);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		JwtAuthenticationResponseDTO response = authenticationService.signin(signInRequestDto);
		response.setMessage(Constants.Messages.SUCCESSFUL_SINGIN);
		response.setError("");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
}
 
