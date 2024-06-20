package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.mongo;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.jupiter.api.Test;


import pl.pojo.tester.api.assertion.Method;

public class MongoEntitiesTest {
	
	
	@Test
	public void gameTest() {
		assertPojoMethodsFor(Game.class).testing(Method.CONSTRUCTOR, Method.GETTER, Method.SETTER, Method.TO_STRING);
	}
	
	@Test
	public void playerTest() {
		assertPojoMethodsFor(Player.class).testing(Method.CONSTRUCTOR, Method.GETTER, Method.SETTER, Method.TO_STRING);
	}
	
}
