package TestAI.openAI.script.controller;

import TestAI.openAI.script.entity.AbstractScriptInfo;
import TestAI.openAI.script.entity.Author;
import TestAI.openAI.script.service.AuthorService;
import TestAI.openAI.script.service.ScriptRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final ScriptRetrievalService scriptRetrievalService;
    private final AuthorService authorService;

    @GetMapping("/api/abstract-script-info")
    public List<AbstractScriptInfo> getAllScript(){
        return scriptRetrievalService.getAllAbstractScriptInfo();
    }

    @GetMapping("/api/script/{articleId}")
    public AbstractScriptInfo getAllScriptInfo(@PathVariable String articleId){
        return scriptRetrievalService.getAbstractScriptInfo(articleId);
    }

    @GetMapping("/api/author/{articleId}")
    public List<Author> getAuthor(@PathVariable String articleId){
        return authorService.findAuthors(articleId);
    }
}
