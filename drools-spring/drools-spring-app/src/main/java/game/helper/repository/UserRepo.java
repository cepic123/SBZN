package game.helper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import game.helper.model.DeveloperStudio;
import game.helper.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
