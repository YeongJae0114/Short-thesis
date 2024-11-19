package TestAI.openAI.script.service.script;

import TestAI.openAI.kci.dto.KciArticleAbstractDto;
import TestAI.openAI.kci.service.KciAbstractService;
import TestAI.openAI.script.repository.GeneratedScriptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScriptGenerationService {
    private final ScriptGenerator scriptGenerator;
    private final KciAbstractService kciAbstractService;
    private final ScriptStorageService scriptStorageService;
    private final GeneratedScriptRepository generatedScriptRepository;

    public List<KciArticleAbstractDto> createAndStoreScript(List<KciArticleAbstractDto> kciArticleAbstractDtoList){
        for (KciArticleAbstractDto articleAbstract : kciArticleAbstractDtoList) {
            String script = scriptGenerator.generateScript(articleAbstract.getAbstractCt());
            articleAbstract.setAbstractCt(script);
            scriptStorageService.saveAbstractScriptInfo(articleAbstract);
        }
        return kciArticleAbstractDtoList;
    }

    public List<KciArticleAbstractDto> createScript(String title, String affiliation){
        // KCI에서 논문의 정보를 받음
        List<KciArticleAbstractDto> abstractList = kciAbstractService.getAllAbstract(title, affiliation);

        for (KciArticleAbstractDto articleAbstract : abstractList) {
            if (generatedScriptRepository.findByArticleId(articleAbstract.getArticleId()).isPresent()){
                log.info("[이미 존재하는 articleId = {}]",articleAbstract.getArticleId());
                continue;
            }

            String script = scriptGenerator.generateScript(articleAbstract.getAbstractCt());
            articleAbstract.setAbstractCt(script);
            scriptStorageService.saveAbstractScriptInfo(articleAbstract);

        }
        return abstractList;
    }



}
