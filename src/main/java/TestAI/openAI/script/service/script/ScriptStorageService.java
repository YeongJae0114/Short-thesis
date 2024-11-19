package TestAI.openAI.script.service.script;

import TestAI.openAI.kci.dto.KciArticleAbstractDto;
import TestAI.openAI.script.entity.AbstractScriptInfo;
import TestAI.openAI.script.entity.Author;
import TestAI.openAI.script.repository.AuthorRepository;
import TestAI.openAI.script.repository.GeneratedScriptRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
@Slf4j
public class ScriptStorageService {
    private final GeneratedScriptRepository generatedScriptRepository;
    private final AuthorRepository authorRepository;

    // 모든 논문 조회
    public List<AbstractScriptInfo> getAllAbstractScriptInfo(){
        return generatedScriptRepository.findAll();
    }

    // articleId로 논문 대본 조회
    public AbstractScriptInfo getAbstractScriptInfo(String articleId) {
        Optional<AbstractScriptInfo> byArticleId = generatedScriptRepository.findByArticleId(articleId);
        return byArticleId.orElse(null);
    }

    // 논문 정보를 저장
    public void saveAbstractScriptInfo(KciArticleAbstractDto kciArticleAbstract){
        AbstractScriptInfo scriptInfo = new AbstractScriptInfo();
        scriptInfo.setArticleId(kciArticleAbstract.getArticleId());
        scriptInfo.setArticleTitle(kciArticleAbstract.getArticleTitle());
        scriptInfo.setShortFormScript(kciArticleAbstract.getAbstractCt());
        scriptInfo.setUrl(kciArticleAbstract.getUrl());
        scriptInfo.setPubYear(kciArticleAbstract.getPubYear());

        generatedScriptRepository.save(scriptInfo);
        saveAuthor(kciArticleAbstract.getAuthors(), kciArticleAbstract.getArticleId());
    }

    // 논문의 저자 저장
    public void saveAuthor(List<String> authorList, String articleId){
        List<Author> authors = stringToAuthor(authorList, articleId);
        authorRepository.saveAll(authors);
    }

    // 논문의 동영상 url 저장 <- articleId 로
    public void saveVideoUrlByArticleId(String articleId, String videoUrl){
        Optional<AbstractScriptInfo> byArticleId = generatedScriptRepository.findByArticleId(articleId);
        if(byArticleId.isPresent()){
            AbstractScriptInfo abstractScriptInfo = byArticleId.get();
            abstractScriptInfo.setVideoUrl(videoUrl);
            generatedScriptRepository.save(abstractScriptInfo);
        }else {
            throw new EntityNotFoundException();
        }
    }


    // 저자를 파싱
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
}
