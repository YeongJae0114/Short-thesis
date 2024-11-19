package TestAI.openAI.kci.controller;

import TestAI.openAI.kci.dto.KciArticleAbstractDto;
import TestAI.openAI.kci.service.KciAbstractService;
import TestAI.openAI.script.service.script.ScriptGenerationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KciController {
    private final KciAbstractService kciAbstractService;
    private final ScriptGenerationService scriptGenerationService;

    @GetMapping("/search")
    public List<KciArticleAbstractDto> searchArticles(@RequestParam String title,
                                                      @RequestParam String affiliation,
                                                      @RequestParam String keyword,
                                                      @RequestParam String author) {
        List<KciArticleAbstractDto> abstractDtoList = kciAbstractService.getAllAbstract(title, affiliation, keyword, author);
        try {
            // 스크립트 생성 및 저장 로직 호출
            abstractDtoList = scriptGenerationService.createAndStoreScript(abstractDtoList);
        } catch (Exception e) {
            // 예외 발생 시 로그 기록
            System.err.println("Error during script generation: " + e.getMessage());
            // 기본 DTO 리스트 반환
            return abstractDtoList;
        }
        // 예외 없이 성공적으로 처리된 경우 null을 반환하지 않도록 적절한 반환값 설정
        return abstractDtoList; // scriptGenerationService의 결과를 반환하려면 이 부분을 수정하세요.
    }
}
