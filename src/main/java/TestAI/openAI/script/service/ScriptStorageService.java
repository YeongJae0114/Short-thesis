package TestAI.openAI.script.service;

import TestAI.openAI.kci.abstractInfo.KciArticleAbstract;
import TestAI.openAI.script.entity.AbstractScriptInfo;
import TestAI.openAI.script.entity.Author;
import TestAI.openAI.script.repository.AuthorRepository;
import TestAI.openAI.script.repository.GeneratedScriptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScriptStorageService {
    private final GeneratedScriptRepository generatedScriptRepository;
    private final AuthorRepository authorRepository;

    public void saveAbstractScriptInfo(KciArticleAbstract kciArticleAbstract){
        AbstractScriptInfo scriptInfo = new AbstractScriptInfo();
        scriptInfo.setArticleId(kciArticleAbstract.getArticleId());
        scriptInfo.setArticleTitle(kciArticleAbstract.getArticleTitle());
        scriptInfo.setShortFormScript(kciArticleAbstract.getAbstractCt());
        scriptInfo.setUrl(kciArticleAbstract.getUrl());
        scriptInfo.setPubYear(kciArticleAbstract.getPubYear());
        generatedScriptRepository.save(scriptInfo);
        saveAuthor(kciArticleAbstract.getAuthors(), kciArticleAbstract.getArticleId());
    }

    public void saveAuthor(List<String> authorList, String articleId){
        List<Author> authors = stringToAuthor(authorList, articleId);
        authorRepository.saveAll(authors);
    }


    private List<Author> stringToAuthor(List<String> preAuthorList, String articleId) {
        List<Author> authors = new ArrayList<>();
        for (String preAuthors : preAuthorList) {
            int startIdx = preAuthors.indexOf("(");
            int endIdx = preAuthors.lastIndexOf(")");
            if (startIdx == -1 || endIdx == -1 || startIdx > endIdx) {
                // 이름이나 소속 정보가 없는 경우 예외 처리
                System.out.println("Invalid author format: " + preAuthors);
                continue;
            }
            Author author = new Author();
            author.setName(preAuthors.substring(0, startIdx).trim());
            author.setAffiliation(preAuthors.substring(startIdx + 1, endIdx).trim());
            author.setArticleId(articleId);
            authors.add(author);
        }
        return authors;
    }

    public Mono<Void> updateVideoUrlByArticleId(String articleId, String videoUrl) {
        Optional<AbstractScriptInfo> existing = generatedScriptRepository.findByArticleId(articleId);
        if (existing.isPresent()) {
            AbstractScriptInfo scriptInfo = existing.get();
            scriptInfo.setVideoUrl(videoUrl);
            generatedScriptRepository.save(scriptInfo);
        } else {
            throw new IllegalArgumentException("Article with the given articleId does not exist.");
        }
        return Mono.empty();
    }
}
