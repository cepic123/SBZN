package game.helper.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import game.helper.model.dto.ReviewDTO;
import game.helper.model.dto.TopListDTO;
import game.helper.services.ReviewService;

@RestController
@RequestMapping("api/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/save")
	public List<TopListDTO> save(@RequestBody ReviewDTO ReviewDTO) {
		return reviewService.save(ReviewDTO);
	}
}
