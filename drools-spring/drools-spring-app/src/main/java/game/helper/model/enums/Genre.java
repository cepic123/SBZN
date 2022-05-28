package game.helper.model.enums;

import javax.persistence.Embeddable;

public enum Genre {
	ADVENTURE, MMO, SPORTS, RPG, FPS, STRATEGY, MOBA;
	
	public Genre getValue(){
	    return this;
	}
}
