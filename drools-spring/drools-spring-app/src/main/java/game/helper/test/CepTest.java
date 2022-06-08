package game.helper.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import game.helper.model.Game;
import game.helper.model.Review;
import game.helper.model.User;
import game.helper.model.events.GameEvent;
import game.helper.model.events.ReviewEvent;

public class CepTest {

	@Test
	public void testCepGameEvent() {
		KieServices ks = KieServices.Factory.get();
    	KieContainer kc = ks.newKieClasspathContainer();
        KieSession kieSession = kc.newKieSession("cepKsession");

		Game g = new Game();
		g.setId(10);
		
		kieSession.insert(new GameEvent(g));
		assertEquals(2, kieSession.fireAllRules());
		kieSession.dispose();
	}

	@Test
	public void testCepFiveReviewsEvent() {
		KieServices ks = KieServices.Factory.get();
    	KieContainer kc = ks.newKieClasspathContainer();
        KieSession kieSession = kc.newKieSession("cepKsession");

        Review r = new Review();
		Game g = new Game();
		g.setId(10);
		r.setGame(g);
		r.setUser(new User());
		r.setScore(1);

		Date now = new Date();  
		r.setTime(now);
		
		kieSession.insert(new ReviewEvent(r));
		kieSession.insert(new ReviewEvent(r));
		kieSession.insert(new ReviewEvent(r));
		kieSession.insert(new ReviewEvent(r));
		kieSession.insert(new ReviewEvent(r));
		
		assertEquals(2, kieSession.fireAllRules());
		kieSession.dispose();
	}
	
	@Test
	public void testCepComplete() {
		KieServices ks = KieServices.Factory.get();
    	KieContainer kc = ks.newKieClasspathContainer();
    	KieSession kieSession = kc.newKieSession("cepKsession");

		Review r = new Review();
		Game g = new Game();
		g.setId(10);
		r.setGame(g);
		r.setUser(new User());
		r.setScore(1);

		Date now = new Date();  
		r.setTime(now);
		
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
		
		kieSession.insert(new GameEvent(g));
		
		assertEquals(7, kieSession.fireAllRules());
		kieSession.dispose();
	}
	
	@Test
	public void testCepTenReviewsComplete() {
		KieServices ks = KieServices.Factory.get();
    	KieContainer kc = ks.newKieClasspathContainer();
    	KieSession kieSession = kc.newKieSession("cepKsession");

		Review r1 = new Review();
		Review r2 = new Review();
		Review r3 = new Review();
		Review r4 = new Review();
		
		Game g1 = new Game();
		g1.setId(10);

		Game g2 = new Game();
		g2.setId(11);

		Game g3 = new Game();
		g3.setId(12);
		
		Game g4 = new Game();
		g4.setId(13);
		
		r1.setGame(g1);
		r1.setUser(new User());
		r1.setScore(1);
		Date now = new Date();  
		r1.setTime(now);

		r2.setGame(g2);
		r2.setUser(new User());
		r2.setScore(1);
		r2.setTime(now);
		
		r3.setGame(g3);
		r3.setUser(new User());
		r3.setScore(1);
		r3.setTime(now);
				
		r4.setGame(g4);
		r4.setUser(new User());
		r4.setScore(1);
		r4.setTime(now);
		
		kieSession.insert(new ReviewEvent(r2));
		kieSession.insert(new ReviewEvent(r2));
		kieSession.insert(new ReviewEvent(r2));
		kieSession.insert(new ReviewEvent(r2));
		
		kieSession.insert(new ReviewEvent(r1));
		kieSession.insert(new ReviewEvent(r1));
		kieSession.insert(new ReviewEvent(r1));
		kieSession.insert(new ReviewEvent(r1));
		
		kieSession.insert(new ReviewEvent(r3));
		
		kieSession.insert(new ReviewEvent(r4));
		
		assertEquals(3, kieSession.fireAllRules());
		kieSession.dispose();
	}
}
