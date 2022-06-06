package game.helper.model.events;

import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import game.helper.model.Game;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("2h30m")
public class GameEvent implements Serializable{

	private static final long serialVersionUID = 1L;
	private Game game;
	private Date executionTime;
	public GameEvent(Game game) {
		super();
		this.game = game;
		this.executionTime = new Date();
	}
	public GameEvent() {
		super();
		this.executionTime = new Date();
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public Date getExecutionTime() {
		return executionTime;
	}
	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}
}
