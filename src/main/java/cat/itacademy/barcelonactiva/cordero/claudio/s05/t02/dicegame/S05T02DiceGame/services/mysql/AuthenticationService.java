package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.JwtAuthenticationResponseDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserSignInRequestDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserSignUpRequestDTO;

public interface AuthenticationService {
 
	JwtAuthenticationResponseDTO signup(UserSignUpRequestDTO signUpRequestDto);
	JwtAuthenticationResponseDTO signin(UserSignInRequestDTO signInRequestDto);
	
}
