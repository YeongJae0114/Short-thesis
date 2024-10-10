package TestAI.openAI.script.service;

import TestAI.openAI.script.entity.AbstractScriptInfo;
import TestAI.openAI.script.repository.GeneratedScriptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScriptRetrievalService {
    private final GeneratedScriptRepository generatedScriptRepository;

    public List<AbstractScriptInfo> getAllAbstractScriptInfo(){
        return generatedScriptRepository.findAll();
    }
}
