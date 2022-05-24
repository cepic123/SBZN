package game.helper.services;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import game.helper.model.Game;

@Service
public class GameService {

	private final KieContainer kieContainer;

	@Autowired
	public GameService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}

	public Game getGame(Game g) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(g);
		kieSession.fireAllRules();
		kieSession.dispose();
		return g;
	}
}
