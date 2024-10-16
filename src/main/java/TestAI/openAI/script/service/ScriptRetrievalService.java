package TestAI.openAI.script.service;

import TestAI.openAI.script.dto.CreateVideoDto;
import TestAI.openAI.script.entity.AbstractScriptInfo;
import TestAI.openAI.script.repository.GeneratedScriptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScriptRetrievalService {
    private final GeneratedScriptRepository generatedScriptRepository;

    public List<AbstractScriptInfo> getAllAbstractScriptInfo(){
        return generatedScriptRepository.findAll();
    }

    public CreateVideoDto createVideoDto() {
        Optional<AbstractScriptInfo> firstByVideoUrlIsNull = generatedScriptRepository.findFirstByVideoUrlIsNull();
        if (firstByVideoUrlIsNull.isPresent()) {
            AbstractScriptInfo abstractScriptInfo = firstByVideoUrlIsNull.get();
            CreateVideoDto createVideoDto = new CreateVideoDto();
            createVideoDto.setArticleId(abstractScriptInfo.getArticleId());
            createVideoDto.setShortFormScript(abstractScriptInfo.getShortFormScript());
            return createVideoDto;
        } else {
            return null;
        }
    }
}
