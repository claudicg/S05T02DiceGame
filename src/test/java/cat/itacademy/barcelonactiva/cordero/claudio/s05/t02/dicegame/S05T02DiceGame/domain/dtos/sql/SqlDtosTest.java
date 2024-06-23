package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;
import org.junit.jupiter.api.Test;
 
import pl.pojo.tester.api.assertion.Method;
 
public class SqlDtosTest {
	
	@Test
	public void jwtAuthenticationResponseDtoTest() {
		assertPojoMethodsFor(JwtAuthenticationResponseDTO.class).testing(Method.CONSTRUCTOR, Method.GETTER, Method.SETTER, Method.TO_STRING);
	}
	
	@Test
	public void userDtoTest() {
		assertPojoMethodsFor(UserDTO.class).testing(Method.CONSTRUCTOR, Method.GETTER, Method.SETTER, Method.TO_STRING);
	}
	
	@Test
	public void userSignInRequestDtoTest() {
		assertPojoMethodsFor(UserSignInRequestDTO.class).testing(Method.CONSTRUCTOR, Method.GETTER, Method.SETTER, Method.TO_STRING);
	}
	
	@Test
	public void userSignUpRequestDtoTest() {
		assertPojoMethodsFor(UserSignUpRequestDTO.class).testing(Method.CONSTRUCTOR, Method.GETTER, Method.SETTER, Method.TO_STRING);
	}
 
}
