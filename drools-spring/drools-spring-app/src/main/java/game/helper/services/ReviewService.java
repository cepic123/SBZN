package game.helper.services;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import game.helper.model.Game;
import game.helper.model.Review;
import game.helper.model.dto.ReviewDTO;
import game.helper.model.dto.TopListDTO;
import game.helper.model.events.GameEvent;
import game.helper.model.events.ReviewEvent;
import game.helper.repository.GameRepo;
import game.helper.repository.ReviewRepo;
import game.helper.repository.UserRepo;

@Service
@Transactional
public class ReviewService {
	
	private final KieContainer kieContainer;
	private final KieSession kieSession;
	private final GameRepo gameRepository;
	private final UserRepo userRepository;
	private final ReviewRepo reviewRepository;
	private final GameService gameService;
	
	public ReviewService(KieContainer kieContainer, GameRepo gameRepository, UserRepo userRepository,
			ReviewRepo reviewRepository, KieSession kieSession, GameService gameService) {
		super();
		this.kieContainer = kieContainer;
		this.gameRepository = gameRepository;
		this.userRepository = userRepository;
		this.reviewRepository = reviewRepository;
		this.kieSession = kieSession;
		this.gameService = gameService;
	}
	
	public List<TopListDTO>  save(ReviewDTO reviewInfo) throws NoSuchElementException {
		Review r = new Review();
		r.setGame(gameRepository.findByName(reviewInfo.getGameName()).get());
		r.setUser(userRepository.findByUsername(reviewInfo.getUserName()).get());
		r.setScore(reviewInfo.getScore());

		Date now = new Date();  
		r.setTime(now);

		reviewRepository.save(r);
		
		kieSession.insert(new ReviewEvent(r));
		
		Integer done =  kieSession.fireAllRules();
		
		if(done >= 3) {
			return gameService.getTopList();
		}
		return null;
	}
	
	public void testCEP(Review r) {
		KieSession kieSession = kieContainer.newKieSession();
		
		kieSession.insert(new ReviewEvent(r));
		kieSession.insert(new ReviewEvent(r));
		kieSession.insert(new ReviewEvent(r));
		kieSession.insert(new ReviewEvent(r));
		kieSession.insert(new ReviewEvent(r));
		
		kieSession.insert(new ReviewEvent(r));
		kieSession.insert(new ReviewEvent(r));
		kieSession.insert(new ReviewEvent(r));
		kieSession.insert(new ReviewEvent(r));
		kieSession.insert(new ReviewEvent(r));
		
		Game g = gameRepository.findById(1).get();
		kieSession.insert(new GameEvent(g));
		kieSession.fireAllRules();
		kieSession.dispose();
	}
}
