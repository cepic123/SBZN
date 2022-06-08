package game.helper.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import game.helper.model.dto.PopularityTemplateDTO;
import game.helper.repository.GameRepo;
import game.helper.repository.ReviewRepo;
import game.helper.repository.StudioRepo;
import game.helper.repository.UserRepo;

@Service
@Transactional
public class TemplateService {
	
	private final KieContainer kieContainer;
	private final GameRepo gameRepository;
	private final UserRepo userRepository;
	private final StudioRepo studioRepository;
	private final ReviewRepo reviewRepository;

	@Autowired
	public TemplateService(KieContainer kieContainer, GameRepo gameRepository, UserRepo userRepository,
			StudioRepo studioRepository, ReviewRepo reviewRepository) {
		this.kieContainer = kieContainer;
		this.gameRepository = gameRepository;
		this.userRepository = userRepository;
		this.studioRepository = studioRepository;
		this.reviewRepository = reviewRepository;
	}
	
	public String compileTemplate(PopularityTemplateDTO dto, String rule){ 
		
        List<PopularityTemplateDTO> templateModels = new ArrayList<>();
        
        templateModels.add(dto);
        
        try (InputStream inputStream = new FileInputStream("../drools-rules/src/main/resources/rules/templates/"+rule)) {
            ObjectDataCompiler compiler = new ObjectDataCompiler();
            String drl = compiler.compile(templateModels, inputStream);
            return drl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public void writeCompiledTemplate(String compiledRules, String rule) throws IOException {
        File file = new File("../drools-rules/src/main/resources/rules/"+rule);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(compiledRules);
        }
    }
	
	public String doJobPopularityAndSimilarity(PopularityTemplateDTO dto, String rule) throws IOException, MavenInvocationException {
        if(rule.equals("popularity")) {
        	String drl = this.compileTemplate(dto, "popularity.drt");
            this.writeCompiledTemplate(drl, "popularity.drl");
        }
        else if(rule.equals("similarity")){
        	String drl = this.compileTemplate(dto, "user-similarity.drt");
            this.writeCompiledTemplate(drl, "user-similarity-rules.drl");
        }
        else {
        	String drl = this.compileTemplate(dto, "top-list.drt");
            this.writeCompiledTemplate(drl, "top-list-rules.drl");
        }
        this.cleanInstall();
        return "Success";
    }
	
	public void cleanInstall() throws MavenInvocationException {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File("../drools-rules/pom.xml"));
        request.setGoals(Arrays.asList("clean", "install"));

        Invoker invoker = new DefaultInvoker();

        invoker.execute(request);
    }
}
