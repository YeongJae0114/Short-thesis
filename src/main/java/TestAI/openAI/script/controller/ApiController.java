package TestAI.openAI.script.controller;

import TestAI.openAI.script.entity.AbstractScriptInfo;
import TestAI.openAI.script.service.ScriptRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final ScriptRetrievalService scriptRetrievalService;

    @GetMapping("/api/abstract-script-info")
    public List<AbstractScriptInfo> getAllScript(){
        return scriptRetrievalService.getAllAbstractScriptInfo();
    }

}
