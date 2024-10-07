package TestAI.openAI.script.service;

import TestAI.openAI.kci.abstractInfo.KciArticleAbstract;
import TestAI.openAI.script.entity.AbstractScriptInfo;
import TestAI.openAI.script.entity.Author;
import TestAI.openAI.script.repository.GeneratedScriptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScriptStorageService {
    private final ScriptGenerationService scriptGenerationService;
    private final GeneratedScriptRepository generatedScriptRepository;

    @Transactional
    public AbstractScriptInfo saveAbstractScriptInfo(KciArticleAbstract kciArticleAbstract){
        AbstractScriptInfo scriptInfo = new AbstractScriptInfo();
        scriptInfo.setArticleId(kciArticleAbstract.getArticleId());
        scriptInfo.setArticleTitle(kciArticleAbstract.getArticleTitle());
        scriptInfo.setShortFormScript(kciArticleAbstract.getAbstractCt());
        scriptInfo.setAuthors(StringToAuthor(kciArticleAbstract.getAuthors()));
        scriptInfo.setUrl(kciArticleAbstract.getUrl());
        scriptInfo.setPubYear(kciArticleAbstract.getPubYear());
        generatedScriptRepository.save(scriptInfo);

        return scriptInfo;
    }

    private List<Author> StringToAuthor(List<String> preAuthorList){
        List<Author> authors = new ArrayList<>();

        for (String preAuthors : preAuthorList) {
            Author author = new Author();
            String[] parts = preAuthors.split("\\(");
            author.setName(parts[0].trim());
            author.setAffiliation(parts[1].replace(")", "").trim());
            authors.add(author);
        }
        return authors;
    }
}
