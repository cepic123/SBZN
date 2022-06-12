package game.helper.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import game.helper.model.DeveloperStudio;

public interface StudioRepo extends JpaRepository<DeveloperStudio, Integer>{

	Optional<DeveloperStudio> findByName(String studioName);

}
