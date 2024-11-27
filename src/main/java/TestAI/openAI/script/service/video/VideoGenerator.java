package TestAI.openAI.script.service.video;

import TestAI.openAI.script.dto.CreateVideoDto;
import TestAI.openAI.script.entity.AbstractScriptInfo;
import TestAI.openAI.script.repository.GeneratedScriptRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VideoGenerator {
    private final GeneratedScriptRepository generatedScriptRepository;

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
    public CreateVideoDto createVideoDto(String articleId){
        Optional<AbstractScriptInfo> firstByVideoUrlIsNull = generatedScriptRepository.findByArticleId(articleId);
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
