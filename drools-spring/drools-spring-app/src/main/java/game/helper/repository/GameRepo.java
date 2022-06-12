package game.helper.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import game.helper.model.Game;

public interface GameRepo extends JpaRepository<Game, Integer>{
	Optional<Game> findByName(String name);
}
