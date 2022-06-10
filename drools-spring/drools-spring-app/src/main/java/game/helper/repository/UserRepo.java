package game.helper.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import game.helper.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);
}
