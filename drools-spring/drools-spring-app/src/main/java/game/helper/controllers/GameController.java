package game.helper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import game.helper.model.Game;
import game.helper.model.enums.Genre;
import game.helper.services.GameService;

@RestController
@RequestMapping("/game")
public class GameController {

	@Autowired
	private GameService gameService;

	@GetMapping("/genre/FPS")
	public Game getGame() {
		Game game = new Game();
		game.setGenre(Genre.FPS);
		return gameService.getGame(game);
	}
}