package game.helper.services;

import java.util.Date;
import java.util.NoSuchElementException;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import game.helper.model.Game;
import game.helper.model.Review;
import game.helper.model.dto.ReviewDTO;
import game.helper.model.events.GameEvent;
import game.helper.model.events.ReviewEvent;
import game.helper.repository.GameRepo;
import game.helper.repository.ReviewRepo;
import game.helper.repository.UserRepo;

@Service
@Transactional
public class ReviewService {
	
	private final KieContainer kieContainer;
	private final GameRepo gameRepository;
	private final UserRepo userRepository;
	private final ReviewRepo reviewRepository;
	
	public ReviewService(KieContainer kieContainer, GameRepo gameRepository, UserRepo userRepository,
			ReviewRepo reviewRepository) {
		super();
		this.kieContainer = kieContainer;
		this.gameRepository = gameRepository;
		this.userRepository = userRepository;
		this.reviewRepository = reviewRepository;
	}
	
	public String save(ReviewDTO reviewInfo) throws NoSuchElementException {
		Review r = new Review();
		r.setGame(gameRepository.findById(reviewInfo.getGameId()).get());
		r.setUser(userRepository.findById(reviewInfo.getUserId()).get());
		r.setScore(reviewInfo.getScore());

		Date now = new Date();  
		r.setTime(now);

		reviewRepository.save(r);
//		KieSession kieSession = kieContainer.newKieSession();
//		kieSession.insert(new ReviewEvent(r));
//		kieSession.insert(new ReviewEvent(r));
//		kieSession.insert(new ReviewEvent(r));
//		kieSession.insert(new ReviewEvent(r));
//		kieSession.fireAllRules();
//		kieSession.dispose();
		testCEP(r);
		return "Succes";
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
