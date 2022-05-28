package game.helper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import game.helper.model.Game;
import game.helper.model.Review;

public interface GameRepo extends JpaRepository<Game, Integer>{

}
