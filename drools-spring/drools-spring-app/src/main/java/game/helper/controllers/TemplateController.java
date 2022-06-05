package game.helper.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.maven.shared.invoker.MavenInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import game.helper.model.dto.ParametersDTO;
import game.helper.model.dto.PopularityTemplateDTO;
import game.helper.model.dto.TopListDTO;
import game.helper.services.GameService;
import game.helper.services.TemplateService;

@RestController
@RequestMapping("api/template")
public class TemplateController {
	
	@Autowired
	private TemplateService templateService;

	@PostMapping("/generatePopularity")
	public String generatePopularity(@RequestBody PopularityTemplateDTO dto) throws IOException, MavenInvocationException {
		return templateService.doJobPopularityAndSimilarity(dto, "popularity");
	}
	
	@PostMapping("/generateUserSimilarity")
	public String generateUserSimilarity(@RequestBody PopularityTemplateDTO dto) throws IOException, MavenInvocationException {
		return templateService.doJobPopularityAndSimilarity(dto, "similarity");
	}
	
	@PostMapping("/generateTopList")
	public String generateTopList(@RequestBody PopularityTemplateDTO dto) throws IOException, MavenInvocationException {
		return templateService.doJobPopularityAndSimilarity(dto, "top-list");
	}
}
