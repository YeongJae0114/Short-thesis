package TestAI.openAI.script.service.script;

import TestAI.openAI.kci.dto.KciArticleAbstractDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScriptGenerationService {
    private final ScriptGenerator scriptGenerator;
    private final ScriptStorageService scriptStorageService;

    public List<KciArticleAbstractDto> createAndStoreScript(List<KciArticleAbstractDto> kciArticleAbstractDtoList){
        for (KciArticleAbstractDto articleAbstract : kciArticleAbstractDtoList) {
            String script = scriptGenerator.generateScript(articleAbstract.getAbstractCt());
            articleAbstract.setAbstractCt(script);
            scriptStorageService.saveAbstractScriptInfo(articleAbstract);
        }
        return kciArticleAbstractDtoList;
    }

}
