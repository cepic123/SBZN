package game.helper.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.HashSet;
import java.util.Set;


import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import game.helper.model.Game;
import game.helper.model.Review;
import game.helper.model.User;

import game.helper.model.dto.GameMatchDTO;

import game.helper.model.dto.GameResultDTO;
import game.helper.model.dto.ListGameResultDTO;
import game.helper.model.dto.ParametersDTO;
import game.helper.model.dto.TopListDTO;
import game.helper.model.dto.UserMatchDTO;
import game.helper.model.dto.UserHistoryDTO;
import game.helper.model.enums.Genre;
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


	public List<TopListDTO> getRecommendations(ParametersDTO parametersDto, Integer userId) {
		
		List<GameResultDTO> gamesFromFirstFlow = firstFlow(parametersDto, userId);
		List<GameResultDTO> gamesFromSecondFlow = secondFlow(userId);

		return null;
//		return gamesFromSecondFlow
//				.stream()
//				.map(TopListDTO::new)
//				.collect(Collectors.toList());
	}

	private List<GameResultDTO> secondFlow(Integer userId) {
		User u = userRepository.findById(userId).orElse(null);
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

		for (GameMatchDTO gmDTO : gmDTOS) {
			System.out.println();
			System.out.println("GAME NAME: " + gmDTO.getGame().getName());
			System.out.println("GAME RATING: " + gmDTO.getRating());

    }
      return null;
		}

	private List<GameResultDTO> firstFlow(ParametersDTO parametersDto, Integer userId) {
		List<Game> games = gameRepository.findAll();
		
		List<GameResultDTO> gamesDTO = games
				.stream()
				.map(game -> new GameResultDTO(game, parametersDto))
				.collect(Collectors.toList());
		
		ListGameResultDTO list = new ListGameResultDTO(gamesDTO);
		UserHistoryDTO history = extractUserHistory(userId);
		System.out.println(history);
		
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(list);
		kieSession.insert(history);
		kieSession.getAgenda().getAgendaGroup("parameters").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		
		return gamesDTO;
	}

	private UserHistoryDTO extractUserHistory(Integer userId) {
		Optional<User> userOpt = userRepository.findById(userId);
		if(userOpt.isEmpty()) {
			throw new RuntimeException("invalid user id");
		}
		User user = userOpt.get();
		
		double sumPrice = 0;
		double sumLength = 0;
		Set<Genre> genres = new HashSet();
		Set<String> studios = new HashSet();
		
		for(Review review : user.getReviews()) {
			genres.addAll(review.getGame().getGenres());
			studios.add(review.getGame().getStudio().getName());
			sumPrice += review.getGame().getPrice();
			sumLength += review.getGame().getLenght();
		}
		
		UserHistoryDTO history = new UserHistoryDTO();
		history.setGenres(genres.stream()
                  .collect(Collectors.toList()));
		
		history.setStudios(studios.stream()
                .collect(Collectors.toList()));
		
		history.setAvgLength(sumLength/user.getReviews().size());
		history.setAvgPrice(sumPrice/user.getReviews().size());
		
		return history;
	}
}
