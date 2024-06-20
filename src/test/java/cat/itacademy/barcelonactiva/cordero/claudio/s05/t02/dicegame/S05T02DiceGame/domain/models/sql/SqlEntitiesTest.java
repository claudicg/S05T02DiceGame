package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.sql;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;
import org.junit.jupiter.api.Test;
 
import pl.pojo.tester.api.assertion.Method;
 
public class SqlEntitiesTest {
	
	@Test
	public void userTest() {
		assertPojoMethodsFor(User.class).testing(Method.CONSTRUCTOR, Method.GETTER, Method.SETTER, Method.TO_STRING);
	}
}
