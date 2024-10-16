package TestAI.openAI.script.service;

import TestAI.openAI.kci.abstractInfo.KciArticleAbstract;
import TestAI.openAI.kci.service.KciAbstractService;
import TestAI.openAI.script.dto.CreateVideoDto;
import TestAI.openAI.script.repository.GeneratedScriptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScriptGenerationService {
    private final OpenAiChatModel openAiChatModel;
    private final KciAbstractService kciAbstractService;
    private final ScriptStorageService scriptStorageService;
    private final GeneratedScriptRepository generatedScriptRepository;

    @Value("${message.instructions}")
    private String instructions;
    @Value("${message.question}")
    private String question;
    @Value("${message.additional_instructions}")
    private String additionalInstructions;

    public List<KciArticleAbstract> createScript(String title, String affiliation){
        // KCI에서 논문의 정보를 받음
        List<KciArticleAbstract> abstractList = kciAbstractService.getAllAbstract(title, affiliation);

        for (KciArticleAbstract articleAbstract : abstractList) {
            if (generatedScriptRepository.findByArticleId(articleAbstract.getArticleId()).isPresent()){
                log.info("[이미 존재하는 articleId = {}]",articleAbstract.getArticleId());
                continue;
            }
            String gpt4Response = openAiChatModel.call(stringMessage(articleAbstract.getAbstractCt()));

            articleAbstract.setAbstractCt(gpt4Response);
            scriptStorageService.saveAbstractScriptInfo(articleAbstract);

        }
        return abstractList;
    }

    private String stringMessage(String introduction){
        return "{\n" +
                "  \"message\": {\n" +
                "    \"instructions\": \"" + instructions + "\",\n" +
                "    \"introduction\": \"" + introduction + "\",\n" +
                "    \"question\": \"" + question + "\",\n" +
                "    \"additional_instructions\": \"" + additionalInstructions + "\"\n" +
                "  }\n" +
                "}";
    }


}
