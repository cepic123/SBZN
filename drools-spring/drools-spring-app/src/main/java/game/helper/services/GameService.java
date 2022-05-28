package game.helper.services;

import java.util.List;
import java.util.stream.Collectors;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import game.helper.model.Game;
import game.helper.model.dto.GameResultDTO;
import game.helper.model.dto.ParametersDTO;
import game.helper.model.dto.TopListDTO;
import game.helper.model.enums.Genre;
import game.helper.repository.GameRepo;
import game.helper.repository.ReviewRepo;
import game.helper.repository.StudioRepo;
import game.helper.repository.UserRepo;

@Service
public class GameService {

	private final KieContainer kieContainer;
	private final GameRepo gameRepository;
	private final UserRepo userRepository;
	private final StudioRepo studioRepository;
	private final ReviewRepo reviewRepository;
	
	@Autowired
	public GameService(KieContainer kieContainer, GameRepo gameRepository, UserRepo userRepository,
			StudioRepo studioRepository, ReviewRepo reviewRepository) {
		this.kieContainer = kieContainer;
		this.gameRepository = gameRepository;
		this.userRepository = userRepository;
		this.studioRepository = studioRepository;
		this.reviewRepository = reviewRepository;
	}

	public Game getGame(Game g) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(g);
		kieSession.fireAllRules();
		kieSession.dispose();
		return g;
	}

	public List<TopListDTO> getRecommendations(ParametersDTO parametersDto, String userId) {
		
		List<GameResultDTO> gamesFromFirstFlow = firstFlow(parametersDto, userId);
		List<GameResultDTO> gamesFromSecondFlow = secondFlow(userId);
		
		return gamesFromFirstFlow
				.stream()
				.map(TopListDTO::new)
				.collect(Collectors.toList());
	}

	private List<GameResultDTO> secondFlow(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<GameResultDTO> firstFlow(ParametersDTO parametersDto, String userId) {
		List<Game> games = gameRepository.findAll();
		
		List<GameResultDTO> gamesDTO = games
				.stream()
				.map(GameResultDTO::new)
				.collect(Collectors.toList());
		
		for(GameResultDTO gameDTO : gamesDTO) {
//			for(Genre genre : parametersDto.getGenres()) {
				KieSession kieSession = kieContainer.newKieSession();
				kieSession.insert(gameDTO);
//				kieSession.insert(genre);
				kieSession.getAgenda().getAgendaGroup("genres").setFocus();
				kieSession.fireAllRules();
				kieSession.dispose();
//			}
		}
		
		return gamesDTO;
	}
}
