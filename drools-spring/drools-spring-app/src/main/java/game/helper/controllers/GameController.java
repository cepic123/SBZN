package game.helper.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import game.helper.model.dto.GameDTO;
import game.helper.model.dto.ParametersDTO;
import game.helper.model.dto.TopListDTO;
import game.helper.services.GameService;

@RestController
@RequestMapping("api/game")
public class GameController {

	@Autowired
	private GameService gameService;

	@PostMapping("/recommendations/{userId}")
	public List<TopListDTO> getGame(@PathVariable("userId") Integer userId,
			@RequestBody ParametersDTO parametersDto) {
		
		return gameService.getRecommendations(parametersDto, userId);
	}
	
	@PostMapping("/topList")
	public List<TopListDTO> topList() {
		return gameService.getTopList();
	}
	
	@PostMapping("/save")
	public List<TopListDTO> save(@RequestBody GameDTO gameDTO) {
		return gameService.save(gameDTO);
	}
}