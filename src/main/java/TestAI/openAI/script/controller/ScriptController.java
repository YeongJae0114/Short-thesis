package TestAI.openAI.script.controller;


import TestAI.openAI.script.service.script.ScriptGenerationService;
import TestAI.openAI.script.service.script.ScriptStorageService;
import TestAI.openAI.script.service.video.ShortFormVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ScriptController {
    private final ScriptGenerationService scriptGenerationService;
    private final ShortFormVideoService shortFormVideoService;
    private final ScriptStorageService scriptStorageService;

    // KCI 논문 조회
//    @GetMapping("/search")
//    public List<KciArticleAbstractDto> searchArticles(@RequestParam String title,
//                                                      @RequestParam String affiliation) {
//        return scriptGenerationService.createScript(title, affiliation);
//    }

    @PostMapping("/sendShortForm")
    public String sendShortForm(){
        shortFormVideoService.sendShortForm();
        return "ok";
    }

    @PostMapping("/save/video")
    public String save(@RequestParam String articleId, @RequestParam String videoUrl){
        scriptStorageService.saveVideoUrlByArticleId(articleId, videoUrl);
        shortFormVideoService.sendShortForm();
        return "Saved successfully";
    }

}
