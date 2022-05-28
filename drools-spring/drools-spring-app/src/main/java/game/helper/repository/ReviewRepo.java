package game.helper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import game.helper.model.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer>{

}
