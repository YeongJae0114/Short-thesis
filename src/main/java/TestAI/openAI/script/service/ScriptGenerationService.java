package TestAI.openAI.script.service;

import TestAI.openAI.kci.abstractInfo.KciArticleAbstract;
import TestAI.openAI.kci.service.KciAbstractService;
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
    private final ShortFormVideoService shortFormVideoService;

    @Value("${message.instructions}")
    private String instructions;
    @Value("${message.question}")
    private String question;
    @Value("${message.additional_instructions}")
    private String additionalInstructions;

    public List<AbstractScriptInfo> createScript(String title, String affiliation){
        List<KciArticleAbstract> abstractList = kciAbstractService.getAllAbstract(title, affiliation);
        List<AbstractScriptInfo> shortFormScriptList = new ArrayList<>();

        for (KciArticleAbstract articleAbstract : abstractList) {
            String abstractText = articleAbstract.getAbstractCt();
            String gpt4Response = openAiChatModel.call(stringMessage(abstractText));

            articleAbstract.setAbstractCt(gpt4Response);
            shortFormScriptList.add(scriptStorageService.saveAbstractScriptInfo(articleAbstract));
        }
        shortFormVideoService.saveVideoUrl(shortFormScriptList);
        return shortFormScriptList;
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
