package TestAI.openAI.script.service;

import TestAI.openAI.script.entity.AbstractScriptInfo;
import TestAI.openAI.script.repository.GeneratedScriptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShortFormVideoService {
    private RestTemplate restTemplate = new RestTemplate();
    private final GeneratedScriptRepository generatedScriptRepository;

    private final String tag = "thesis";
    private final String apiUrl = "https://widely-select-polliwog.ngrok-free.app/movie";

    public void saveVideoUrl(List<AbstractScriptInfo> abstractScriptInfoList){
        for(AbstractScriptInfo abstractScriptInfo : abstractScriptInfoList){
            String script = abstractScriptInfo.getShortFormScript();
            String requestUrl = apiUrl + "?text="+script + "&tag="+tag;
            String videoUrl = restTemplate.postForObject(requestUrl, null, String.class);
            if (videoUrl != null) {
                AbstractScriptInfo scriptInfo = generatedScriptRepository.findByIdOrThrow(abstractScriptInfo.getId());
                scriptInfo.setVideoUrl(videoUrl);
                generatedScriptRepository.save(scriptInfo);
            }
        }

    }
}
