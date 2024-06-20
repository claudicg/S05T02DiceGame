package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.repositories.mongo;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.mongo.Player;
import jakarta.transaction.Transactional;

@Repository("PlayerRepository")
public interface PlayerRepository extends MongoRepository<Player, String> {

	
	// Uso native query para que me devuelva el número de documentos borrados, mediante @Modifying
	@Transactional
	@Modifying
	@Query(value="{userId : ?0}", delete = true)
	int deletePlayer(@Param(value = "userId") int userId);
		
}
