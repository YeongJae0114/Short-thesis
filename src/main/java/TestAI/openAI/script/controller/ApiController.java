package TestAI.openAI.script.controller;

import TestAI.openAI.script.entity.AbstractScriptInfo;
import TestAI.openAI.script.service.script.ScriptStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final ScriptStorageService scriptStorageService;

    @GetMapping("/api/script")
    public List<AbstractScriptInfo> getAllScript(){
        return scriptStorageService.getAllAbstractScriptInfo();
    }

    @GetMapping("/api/script/{articleId}")
    public AbstractScriptInfo getAllScriptInfo(@PathVariable String articleId){
        return scriptStorageService.getAbstractScriptInfo(articleId);
    }




}
