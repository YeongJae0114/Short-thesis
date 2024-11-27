package TestAI.openAI.script.controller;


import TestAI.openAI.script.service.script.ScriptGenerationService;
import TestAI.openAI.script.service.script.ScriptStorageService;
import TestAI.openAI.script.service.video.ShortFormVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ScriptController {
    private final ShortFormVideoService shortFormVideoService;
    private final ScriptStorageService scriptStorageService;

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

    @GetMapping("/sendShortForm/{articleId}")
    public String sendShortFormForId(@PathVariable String articleId){
        shortFormVideoService.sendShortForm(articleId);
        return "ok";
    }

}
