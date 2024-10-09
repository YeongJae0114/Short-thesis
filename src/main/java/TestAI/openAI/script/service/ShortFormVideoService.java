package TestAI.openAI.script.service;

import TestAI.openAI.script.dto.CreateVideoDto;
import TestAI.openAI.script.repository.GeneratedScriptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShortFormVideoService {
    private RestTemplate restTemplate = new RestTemplate();
    private final GeneratedScriptRepository generatedScriptRepository;

    private final String tag = "thesis";
    private final String apiUrl = "https://widely-select-polliwog.ngrok-free.app/movie";

    public void saveVideoUrl(List<CreateVideoDto> createVideoDtoList){
        for(CreateVideoDto createVideoDto : createVideoDtoList){
            String script = createVideoDto.getShortFormScript();
            String thesis_id = createVideoDto.getArticleId();
            String requestUrl = apiUrl + "?text="+script + "&tag="+tag + "&thesis_id="+thesis_id;

            restTemplate.postForObject(requestUrl, null, String.class);
        }
        log.info("[createVideoDto] 전달 완료");
    }
}
