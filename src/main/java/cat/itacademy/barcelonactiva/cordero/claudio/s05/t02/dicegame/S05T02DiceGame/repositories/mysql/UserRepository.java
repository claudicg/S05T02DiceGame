package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.repositories.mysql;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.sql.User;
import jakarta.transaction.Transactional;
 
 
@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);
	
	//Uso native query para que me devuelva el número de registros borrados, mediante @Modifying
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM users WHERE user_id = :userId", nativeQuery = true)
	int deleteUser(@Param(value = "userId") int userId);
 
}
