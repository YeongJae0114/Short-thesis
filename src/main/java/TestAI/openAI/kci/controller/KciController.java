package TestAI.openAI.kci.controller;

import TestAI.openAI.kci.dto.KciArticleAbstractDto;
import TestAI.openAI.kci.service.KciAbstractService;
import TestAI.openAI.script.service.script.ScriptGenerationService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class KciController {
    private final KciAbstractService kciAbstractService;
    private final ScriptGenerationService scriptGenerationService;

    @GetMapping("/search")
    public String searchArticles(@RequestParam String title,
                                 @RequestParam String affiliation,
                                 @RequestParam String keyword,
                                 @RequestParam String author,
                                 Model model) {
        // 초록 리스트 가져오기
        List<KciArticleAbstractDto> originalAbstracts = kciAbstractService.getAllAbstract(title, affiliation, keyword, author);

            // 스크립트 생성
        List<KciArticleAbstractDto> afterConversions = scriptGenerationService.createAndStoreScript(originalAbstracts);

        model.addAttribute("comparisonList", afterConversions);
        return "script"; // Thymeleaf 템플릿 이름
    }



}
