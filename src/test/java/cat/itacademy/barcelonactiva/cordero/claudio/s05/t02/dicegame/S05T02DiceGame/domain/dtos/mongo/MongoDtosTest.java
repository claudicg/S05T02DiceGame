package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.jupiter.api.Test;


import pl.pojo.tester.api.assertion.Method;

public class MongoDtosTest {
	
	@Test
	public void deleteResponseDTOTest() {
		assertPojoMethodsFor(DeleteResponseDTO.class).testing(Method.CONSTRUCTOR, Method.GETTER, Method.SETTER, Method.TO_STRING);
	}

	@Test
	public void gameDtoTest() {
		assertPojoMethodsFor(GameDTO.class).testing(Method.CONSTRUCTOR, Method.GETTER, Method.SETTER, Method.TO_STRING);
	}
	
	@Test
	public void playerDtoTest() {
		assertPojoMethodsFor(PlayerDTO.class).testing(Method.CONSTRUCTOR, Method.GETTER, Method.SETTER, Method.TO_STRING);
	}
	
	@Test
	public void playerGamesResponseDtoTest() {
		assertPojoMethodsFor(PlayerGamesResponseDTO.class).testing(Method.CONSTRUCTOR, Method.GETTER, Method.SETTER, Method.TO_STRING);
	}
	
	@Test
	public void playerResponseDtoTest() {
		assertPojoMethodsFor(PlayerResponseDTO.class).testing(Method.CONSTRUCTOR, Method.GETTER, Method.SETTER, Method.TO_STRING);
	}
	
	@Test
	public void playersSuccessPercentageResponseDtoTest() {
		assertPojoMethodsFor(PlayersSuccessPercentageResponseDTO.class).testing(Method.CONSTRUCTOR, Method.GETTER, Method.SETTER, Method.TO_STRING);
	}
	
	@Test
	public void playerSuccessPercentageDtoTest() {
		assertPojoMethodsFor(PlayerSuccessPercentageDTO.class).testing(Method.CONSTRUCTOR, Method.GETTER, Method.SETTER, Method.TO_STRING);
	}
	
}
