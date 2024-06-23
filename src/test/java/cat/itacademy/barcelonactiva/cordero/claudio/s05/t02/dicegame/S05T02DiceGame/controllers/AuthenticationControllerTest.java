package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.controllers;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.JwtAuthenticationResponseDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserSignInRequestDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserSignUpRequestDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions.InvalidEmailException;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql.AuthenticationService;


public class AuthenticationControllerTest {
	
	@InjectMocks
	private AuthenticationController authenticationController;
	
	@Mock
	private AuthenticationService authenticationService;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
		
	@Test
	public void signUpOkTest() throws InvalidEmailException {
		
		UserSignUpRequestDTO userSignUpRequestDto = new UserSignUpRequestDTO();
		userSignUpRequestDto.setEmail("admin1@exemple.com");
		userSignUpRequestDto.setNickname("Juan");
		userSignUpRequestDto.setPassword("1234");
		
		JwtAuthenticationResponseDTO jwtDto = new JwtAuthenticationResponseDTO();
		jwtDto.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUBleGVtcGxlLmNvbSIsImlhdCI6MTcxODU2NzYzNywiZXhwIjoxNzE4NjUyMjM3fQ.4XjhTfQK1MmWUsbLshEO9TLzKfQY7Gn5Odj4RD5T-8M");
		
		when(authenticationService.signup(Mockito.any())).thenReturn(jwtDto);
		
		ResponseEntity<JwtAuthenticationResponseDTO> result = authenticationController.signup(userSignUpRequestDto);
		
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertEquals("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUBleGVtcGxlLmNvbSIsImlhdCI6MTcxODU2NzYzNywiZXhwIjoxNzE4NjUyMjM3fQ.4XjhTfQK1MmWUsbLshEO9TLzKfQY7Gn5Odj4RD5T-8M", result.getBody().getToken());
		
	}
	
	
	@Test
	public void signInOkTest() throws InvalidEmailException {
		
		UserSignInRequestDTO userSignInRequestDto = new UserSignInRequestDTO();
		userSignInRequestDto.setEmail("admin1@exemple.com");
		userSignInRequestDto.setPassword("1234");
		
		JwtAuthenticationResponseDTO jwtDto = new JwtAuthenticationResponseDTO();
		jwtDto.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUBleGVtcGxlLmNvbSIsImlhdCI6MTcxODU2NzYzNywiZXhwIjoxNzE4NjUyMjM3fQ.4XjhTfQK1MmWUsbLshEO9TLzKfQY7Gn5Odj4RD5T-8M");
		
		when(authenticationService.signin(Mockito.any())).thenReturn(jwtDto);
		
		ResponseEntity<JwtAuthenticationResponseDTO> result = authenticationController.signin(userSignInRequestDto);
		
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertEquals("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUBleGVtcGxlLmNvbSIsImlhdCI6MTcxODU2NzYzNywiZXhwIjoxNzE4NjUyMjM3fQ.4XjhTfQK1MmWUsbLshEO9TLzKfQY7Gn5Odj4RD5T-8M", result.getBody().getToken());
		
	}

}
