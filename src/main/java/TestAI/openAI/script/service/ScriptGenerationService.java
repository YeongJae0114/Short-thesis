package TestAI.openAI.script.service;

import TestAI.openAI.kci.abstractInfo.KciArticleAbstract;
import TestAI.openAI.kci.service.KciAbstractService;
import TestAI.openAI.script.dto.CreateVideoDto;
import TestAI.openAI.script.entity.AbstractScriptInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ScriptGenerationService {
    private final OpenAiChatModel openAiChatModel;
    private final KciAbstractService kciAbstractService;
    private final ScriptStorageService scriptStorageService;

    @Value("${message.instructions}")
    private String instructions;
    @Value("${message.question}")
    private String question;
    @Value("${message.additional_instructions}")
    private String additionalInstructions;

    public List<CreateVideoDto> createScript(String title, String affiliation){
        // KCI에서 논문의 정보를 받음
        List<KciArticleAbstract> abstractList = kciAbstractService.getAllAbstract(title, affiliation);
        List<CreateVideoDto> createVideoList = new ArrayList<>();
        for (KciArticleAbstract articleAbstract : abstractList) {
            CreateVideoDto createVideoDto = new CreateVideoDto();

            String abstractText = articleAbstract.getAbstractCt();
            String gpt4Response = openAiChatModel.call(stringMessage(abstractText));
            createVideoDto.setArticleId(articleAbstract.getArticleId());
            createVideoDto.setShortFormScript(gpt4Response);
            articleAbstract.setAbstractCt(gpt4Response);
            scriptStorageService.saveAbstractScriptInfo(articleAbstract);
            createVideoList.add(createVideoDto);
        }
        return createVideoList;
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
