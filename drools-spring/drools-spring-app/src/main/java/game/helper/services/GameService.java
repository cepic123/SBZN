package game.helper.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import game.helper.model.Game;
import game.helper.model.Review;
import game.helper.model.User;
import game.helper.model.dto.GameMatchDTO;
import game.helper.model.dto.GameResultDTO;
import game.helper.model.dto.ParametersDTO;
import game.helper.model.dto.TopListDTO;
import game.helper.model.dto.UserMatchDTO;
import game.helper.repository.GameRepo;
import game.helper.repository.ReviewRepo;
import game.helper.repository.StudioRepo;
import game.helper.repository.UserRepo;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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

		return null;
//		return gamesFromSecondFlow
//				.stream()
//				.map(TopListDTO::new)
//				.collect(Collectors.toList());
	}

	private List<GameResultDTO> secondFlow(String userId) {
		User u = userRepository.findById(Integer.parseInt(userId)).orElse(null);
		List<User> users = userRepository.findAll();
		List<UserMatchDTO> umDTOS = new ArrayList<UserMatchDTO>();
		
		KieSession kieSession = kieContainer.newKieSession();

		for (User other_user : users) {
			if (other_user.getId() != Integer.parseInt(userId)) {
				UserMatchDTO umDTO = new UserMatchDTO();
				umDTO.setUser(u);
				umDTO.setReviews(other_user.getReviews());
				umDTO.setOtherUser(other_user);
				kieSession.insert(umDTO);
				umDTOS.add(umDTO);
			}
		}
		kieSession.getAgenda().getAgendaGroup("users").setFocus();
		kieSession.fireAllRules();
		
		List<Game> games = gameRepository.findAll();
		List<Game> userGames = u.getReviews().stream().map(r -> r.getGame()).collect(Collectors.toList());
		List<GameMatchDTO> gmDTOS = new ArrayList<GameMatchDTO>();
		
		for (Game g : games) {
			if(userGames.contains(g)) continue;
			GameMatchDTO gmDTO = new GameMatchDTO();
			gmDTO.setGame(g);
			for (UserMatchDTO umDTO : umDTOS) {
				kieSession.insert(gmDTO);
				kieSession.insert(umDTO);
			}
			gmDTOS.add(gmDTO);
		}

		kieSession.getAgenda().getAgendaGroup("games").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();

//		for (UserMatchDTO umDTO : umDTOS) {
//			System.out.println("USERNAME: " + umDTO.getOtherUser().getUsername());
//			System.out.println("MATCHES: " + umDTO.getNo_matches());
//			System.out.println("MATCHES: " + umDTO.getMatchCoeficient());
//		}
		for (GameMatchDTO gmDTO : gmDTOS) {
			System.out.println();
			System.out.println("GAME NAME: " + gmDTO.getGame().getName());
			System.out.println("GAME RATING: " + gmDTO.getRating());
//			System.out.println("LOW:  " + gmDTO.isgLow());
//			System.out.println("MID: " + gmDTO.isgMid());
//			System.out.println("HIGH: " + gmDTO.isgHigh());
		}
		return null;
	}

	private List<GameResultDTO> firstFlow(ParametersDTO parametersDto, String userId) {
		List<Game> games = gameRepository.findAll();

		List<GameResultDTO> gamesDTO = games.stream().map(GameResultDTO::new).collect(Collectors.toList());

		for (GameResultDTO gameDTO : gamesDTO) {
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
