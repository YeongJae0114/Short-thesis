package TestAI.openAI.script.service;

import TestAI.openAI.script.dto.CreateVideoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShortFormVideoService {
    private final ScriptStorageService scriptStorageService;
    private RestTemplate restTemplate = new RestTemplate();
    private final WebClient webClient;

    private final String tag = "thesis";
    private final String baseApiUrl = "https://widely-select-polliwog.ngrok-free.app/";
    private final String createUrl = "movie";

    public void saveVideoUrl(List<CreateVideoDto> createVideoDtoList){
        for(CreateVideoDto createVideoDto : createVideoDtoList){
            String script = createVideoDto.getShortFormScript();
            String thesis_id = createVideoDto.getArticleId();
            String requestUrl = baseApiUrl+createUrl+ "?text="+script + "&tag="+tag + "&thesis_id="+thesis_id;

            String videoUrl = restTemplate.postForObject(requestUrl, null, String.class);
            System.out.println(baseApiUrl + videoUrl);
        }
        log.info("[createVideoDto] 전달 완료");
    }

    public void saveVideoUrl2(List<CreateVideoDto> createVideoDtoList) {
        // Flux로 요청을 순차적으로 처리
        Flux.fromIterable(createVideoDtoList)
                .concatMap(this::sendVideoRequest)  // 하나의 요청이 끝난 후에 다음 요청 실행
                .doOnComplete(() -> log.info("[모든 createVideoDto 전달 완료]"))
                .subscribe();
    }

    private Mono<Void> sendVideoRequest(CreateVideoDto createVideoDto) {
        String script = createVideoDto.getShortFormScript();
        String thesis_id = createVideoDto.getArticleId();
        String requestUrl = createUrl + "?text=" + script + "&tag=" + tag + "&thesis_id=" + thesis_id;
        log.info("[sendVideoRequest] 시작: {}", thesis_id);

        return webClient.post()
                .uri(requestUrl)
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(videoUrl -> {
                    log.info("[sendVideoRequest] 응답 수신: {}", videoUrl);
                    return scriptStorageService.updateVideoUrlByArticleId(thesis_id, videoUrl);
                })
                .then();  // 각 요청이 끝난 후에만 다음으로 넘어감
    }
}
