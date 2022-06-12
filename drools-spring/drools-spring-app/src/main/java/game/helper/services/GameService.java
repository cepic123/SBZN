package game.helper.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
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
import game.helper.model.dto.CombiningDTO;
import game.helper.model.dto.GameDTO;
import game.helper.model.dto.GameMatchDTO;
import game.helper.model.dto.GameResultDTO;
import game.helper.model.dto.ListGameResultDTO;
import game.helper.model.dto.ParametersDTO;
import game.helper.model.dto.ResultListDTO;
import game.helper.model.dto.TLDTO;
import game.helper.model.dto.TopListDTO;
import game.helper.model.dto.UserMatchDTO;
import game.helper.model.dto.UserHistoryDTO;
import game.helper.model.enums.Genre;
import game.helper.model.enums.RuleStatus;
import game.helper.model.events.GameEvent;
import game.helper.repository.GameRepo;
import game.helper.repository.ReviewRepo;
import game.helper.repository.StudioRepo;
import game.helper.repository.UserRepo;

@Service
@Transactional
public class GameService {

	private final KieContainer kieContainer;
	private final KieSession kieSession;
	private final GameRepo gameRepository;
	private final UserRepo userRepository;
	private final StudioRepo studioRepository;
	private final ReviewRepo reviewRepository;

	@Autowired
	public GameService(KieContainer kieContainer, GameRepo gameRepository, UserRepo userRepository,
			StudioRepo studioRepository, ReviewRepo reviewRepository, KieSession kieSession) {
		this.kieContainer = kieContainer;
		this.gameRepository = gameRepository;
		this.userRepository = userRepository;
		this.studioRepository = studioRepository;
		this.reviewRepository = reviewRepository;
		this.kieSession = kieSession;
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
		ListGameResultDTO list = new ListGameResultDTO(gamesFromSecondFlow);
		list.sortIt();
		list.updateRankings();
		
		Map<String, CombiningDTO> combinedMap = new HashMap<>();
		
		for(GameResultDTO game : gamesFromFirstFlow) {
			CombiningDTO c = new CombiningDTO();
			c.setGame(game.getGame());
			c.setPointsFirstFlow(game.getPoints());
			c.setRankFirstFlow(game.getRank());
			combinedMap.put(game.getGame().getName(), c);
		}
		
		for(GameResultDTO game : gamesFromSecondFlow) {
			if(combinedMap.containsKey(game.getGame().getName())) {
				CombiningDTO c = combinedMap.get(game.getGame().getName());
				c.setGame(game.getGame());
				c.setPointsSecondFlow(game.getPoints());
				c.setRankSecondFlow(game.getRank());
				combinedMap.put(game.getGame().getName(), c);
			}
			else {
				CombiningDTO c = new CombiningDTO();
				c.setGame(game.getGame());
				c.setPointsSecondFlow(game.getPoints());
				c.setRankSecondFlow(game.getRank());
				combinedMap.put(game.getGame().getName(), c);
			}
			
		}
		
		List<CombiningDTO> valueList = new ArrayList<>(combinedMap.values());
		
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("combining").setFocus();
		
		for(CombiningDTO dto : valueList) {
			kieSession.insert(dto);
		}
		
		kieSession.fireAllRules();
		kieSession.dispose();

		ResultListDTO result = new ResultListDTO(valueList);
		kieSession = kieContainer.newKieSession();
		kieSession.insert(result);
		kieSession.getAgenda().getAgendaGroup("combining_enums").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		
		List<CombiningDTO> resultList = new ArrayList<>();
		kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("sort").setFocus();
		kieSession.setGlobal("resultList", resultList);
		for(CombiningDTO dto : result.getList()) {
			kieSession.insert(dto);
		}
		kieSession.fireAllRules();
		kieSession.dispose();
		
		result.setList(resultList);
		
		kieSession = kieContainer.newKieSession();
		kieSession.insert(result);
		kieSession.getAgenda().getAgendaGroup("last_rule").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		
		List<CombiningDTO> resultListFinal = new ArrayList<>();
		kieSession = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("sort").setFocus();
		kieSession.setGlobal("resultList", resultListFinal);
		for(CombiningDTO dto : result.getResultList()) {
			kieSession.insert(dto);
		}
		kieSession.fireAllRules();
		kieSession.dispose();
		
		return resultListFinal.stream().map(TopListDTO::new).collect(Collectors.toList());
	}

	private List<GameResultDTO> secondFlow(Integer userId) {
		User u = userRepository.findById(userId).orElse(null);
		List<User> users = userRepository.findAll();
		List<UserMatchDTO> umDTOS = new ArrayList<UserMatchDTO>();

		KieSession kieSession = kieContainer.newKieSession();

		for (User other_user : users) {
			if (other_user.getId() != userId) {
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
			if (userGames.contains(g))
				continue;
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
		
		List<GameResultDTO> gamesDTO = gmDTOS.stream().map(game -> new GameResultDTO(game.getGame(), game.getRating()))
				.collect(Collectors.toList());
		
		return gamesDTO;
	}

	private List<GameResultDTO> firstFlow(ParametersDTO parametersDto, Integer userId) {
		List<Game> games = gameRepository.findAll();
		List<Game> gamesForFlow = new ArrayList<>();
		
		Optional<User> userOpt = userRepository.findById(userId);
		if (userOpt.isEmpty()) {
			throw new RuntimeException("invalid user id");
		}
		User user = userOpt.get();
		List<Game> userGames = user.getReviews().stream().map(r -> r.getGame()).collect(Collectors.toList());

		for(Game game:games) {
			if(userGames.contains(game)) {
				continue;
			}
			gamesForFlow.add(game);
		}
		
		List<GameResultDTO> gamesDTO = gamesForFlow.stream().map(game -> new GameResultDTO(game, parametersDto))
				.collect(Collectors.toList());

		ListGameResultDTO list = new ListGameResultDTO(gamesDTO);
		list.setStatus(RuleStatus.GENRES);
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
		if (userOpt.isEmpty()) {
			throw new RuntimeException("invalid user id");
		}
		User user = userOpt.get();

		double sumPrice = 0;
		double sumLength = 0;
		Set<Genre> genres = new HashSet<Genre>();
		Set<String> studios = new HashSet<String>();

		for (Review review : user.getReviews()) {
			genres.addAll(review.getGame().getGenres());
			studios.add(review.getGame().getStudio().getName());
			sumPrice += review.getGame().getPrice();
			sumLength += review.getGame().getLenght();
		}

		UserHistoryDTO history = new UserHistoryDTO();
		history.setGenres(genres.stream().collect(Collectors.toList()));

		history.setStudios(studios.stream().collect(Collectors.toList()));

		history.setAvgLength(sumLength / user.getReviews().size());
		history.setAvgPrice(sumPrice / user.getReviews().size());

		return history;
	}

	public List<TopListDTO> getTopList() {
		KieSession kieSession = kieContainer.newKieSession();
		
		List<Game> games = gameRepository.findAll();
		List<TLDTO> tlDTOS = new ArrayList<TLDTO>();
		
		for(Game g: games) {
			TLDTO tlDTO = new TLDTO();
			tlDTO.setGame(g);
			tlDTO.setReviews(g.getReviews());
			tlDTOS.add(tlDTO);
			kieSession.insert(tlDTO);	
		}
		
		kieSession.getAgenda().getAgendaGroup("top-list").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		
		List<TopListDTO> topList = new ArrayList<TopListDTO>();
		for(TLDTO tlDTO: tlDTOS) {
			System.out.println("GAME NAME: " + tlDTO.getGame().getName());
			System.out.println("GAME RATING: " + tlDTO.getRating());
			System.out.println("REVIEW CATEGORY: " + tlDTO.getReviewCategory());
			System.out.println("STUDIO RATING: " + tlDTO.getStudioRating());
			
			topList.add(new TopListDTO(tlDTO.getGame().getName(), tlDTO.getRating()));
		}
		return topList;
	}
	
	public List<TopListDTO> save(GameDTO gameInfo) throws NoSuchElementException {
		Game g = new Game();
		g.setName(gameInfo.getName());
		g.setLenght(gameInfo.getLenght());
		g.setStudio(studioRepository.findByName(gameInfo.getStudioName()).get());
		g.setPrice(gameInfo.getPrice());
		g.setMultiplayer(gameInfo.isMultiplayer());
		g.setOnline(gameInfo.isOnline());
		g.setGenre(gameInfo.getGenres());
		
		gameRepository.save(g);

		kieSession.insert(new GameEvent(g));
		//System.out.println(kieSession.fireAllRules());
		Integer done =  kieSession.fireAllRules();
		
		if(done >= 3) {
			return getTopList();
		}
		return null;
	}
}
