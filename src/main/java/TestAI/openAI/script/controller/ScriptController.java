package TestAI.openAI.script.controller;


import TestAI.openAI.kci.abstractInfo.KciArticleAbstract;
import TestAI.openAI.script.dto.CreateVideoDto;
import TestAI.openAI.script.service.ScriptGenerationService;
import TestAI.openAI.script.service.ScriptRetrievalService;
import TestAI.openAI.script.service.ScriptStorageService;
import TestAI.openAI.script.service.ShortFormVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ScriptController {
    private final ScriptGenerationService scriptGenerationService;
    private final ShortFormVideoService shortFormVideoService;
    private final ScriptRetrievalService scriptRetrievalService;
    private final ScriptStorageService scriptStorageService;

    @GetMapping("/search")
    public List<KciArticleAbstract> searchArticles(@RequestParam String title,
                                               @RequestParam String affiliation) {
        List<KciArticleAbstract> script = scriptGenerationService.createScript(title, affiliation);

        return script;
    }

    @PostMapping("/sendShortForm")
    public String sendShortForm(){
        shortFormVideoService.sendShortForm();
        return "ok";
    }

    @PostMapping("/save/video")
    public String save(@RequestParam String articleId, @RequestParam String videoUrl){
        scriptStorageService.updateVideoUrlByArticleId(articleId, videoUrl);
        shortFormVideoService.sendShortForm();
        return "Saved successfully";
    }

}
