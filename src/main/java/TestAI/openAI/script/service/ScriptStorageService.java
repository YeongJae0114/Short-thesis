package TestAI.openAI.script.service;

import TestAI.openAI.kci.abstractInfo.KciArticleAbstract;
import TestAI.openAI.script.entity.AbstractScriptInfo;
import TestAI.openAI.script.entity.Author;
import TestAI.openAI.script.repository.AuthorRepository;
import TestAI.openAI.script.repository.GeneratedScriptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScriptStorageService {
    private final GeneratedScriptRepository generatedScriptRepository;
    private final AuthorRepository authorRepository;

    public AbstractScriptInfo saveAbstractScriptInfo(KciArticleAbstract kciArticleAbstract){
        AbstractScriptInfo scriptInfo = new AbstractScriptInfo();
        scriptInfo.setArticleId(kciArticleAbstract.getArticleId());
        scriptInfo.setArticleTitle(kciArticleAbstract.getArticleTitle());
        scriptInfo.setShortFormScript(kciArticleAbstract.getAbstractCt());
        scriptInfo.setUrl(kciArticleAbstract.getUrl());
        scriptInfo.setPubYear(kciArticleAbstract.getPubYear());
        Optional<AbstractScriptInfo> existing = generatedScriptRepository.findByArticleId(scriptInfo.getArticleId());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 articleId 입니다.");
        }
        generatedScriptRepository.save(scriptInfo);
        saveAuthor(kciArticleAbstract.getAuthors(), kciArticleAbstract.getArticleId());

        return scriptInfo;
    }

    public void saveAuthor(List<String> authorList, String articleId){
        List<Author> authors = stringToAuthor(authorList, articleId);
        authorRepository.saveAll(authors);
    }


    private List<Author> stringToAuthor(List<String> preAuthorList, String articleId){
        List<Author> authors = new ArrayList<>();
        for (String preAuthors : preAuthorList) {
            Author author = new Author();
            String[] parts = preAuthors.split("\\(");
            author.setName(parts[0].trim());
            author.setAffiliation(parts[1].replace(")", "").trim());
            author.setArticleId(articleId);
            authors.add(author);
        }
        return authors;
    }
}
