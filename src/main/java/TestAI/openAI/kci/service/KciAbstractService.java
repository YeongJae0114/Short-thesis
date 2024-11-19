package TestAI.openAI.kci.service;

import TestAI.openAI.kci.dto.KciArticleAbstractDto;
import TestAI.openAI.kci.xmlResponse.MetaData;
import TestAI.openAI.kci.xmlResponse.Record;
import TestAI.openAI.kci.xmlResponse.recode.articleInfo.AbstractContent;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class KciAbstractService {
    @Value("${kci.base-api-url}")
    private String BASE_API_URL;
    @Value("${kci.api-key}")
    private String API_KEY;
    private final RestTemplate restTemplate = new RestTemplate();

    public List<KciArticleAbstractDto> getAllAbstract(String title, String affiliation, String keyword, String author) {
        // 메서드 호출로 URI 구성
        URI uri = buildUri(title, affiliation, keyword, author);
        // RestTemplate을 사용하여 XML 응답 받기
        String xmlResponse = restTemplate.getForObject(uri, String.class);
        return parseXmlResponse(xmlResponse);
    }

    // XML 응답 파싱
    private List<KciArticleAbstractDto> parseXmlResponse(String xmlResponse) {
        List<KciArticleAbstractDto> abstractInfoList = new ArrayList<>();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(MetaData.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            MetaData metaData = (MetaData) unmarshaller.unmarshal(new StringReader(xmlResponse));

            if (metaData.getOutputData() != null && metaData.getOutputData().getRecords() != null) {
                for (Record record : metaData.getOutputData().getRecords()) {
                    if (record.getArticleInfo() != null && record.getArticleInfo().getAbstractGroup() != null) {
                        KciArticleAbstractDto abstractInfo = new KciArticleAbstractDto();
                        abstractInfo.setArticleId(record.getArticleInfo().getArticleId());
                        abstractInfo.setArticleTitle(record.getArticleInfo().getOriginalTitle());
                        abstractInfo.setAuthors(extractAuthors(record));
                        abstractInfo.setAbstractCt(extractOriginalAbstract(record));
                        abstractInfo.setUrl(record.getArticleInfo().getUrl());
                        abstractInfo.setPubYear(record.getJournalInfo().getPubYear());

                        abstractInfoList.add(abstractInfo);
                    }
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return abstractInfoList;
    }

    // 저자 리스트 추출 메서드
    private List<String> extractAuthors(Record record) {
        List<String> authors = new ArrayList<>();
        if (record.getArticleInfo().getAuthorGroup() != null) {
            record.getArticleInfo().getAuthorGroup().getAuthors().forEach(author -> authors.add(author.getName()));
        }
        return authors;
    }

    // 원본 초록 추출 메서드
    private String extractOriginalAbstract(Record record) {
        if (record.getArticleInfo().getAbstractGroup() != null) {
            for (AbstractContent abs : record.getArticleInfo().getAbstractGroup().getAbstracts()) {
                if ("original".equals(abs.getLang())) {
                    return abs.getContent();
                }
            }
        }
        return "Original Abstract not found";
    }

    // uri 동적으로 생성
    private URI buildUri(String title, String affiliation, String keyword, String author) {
        return UriComponentsBuilder.fromHttpUrl(BASE_API_URL)
                .queryParam("apiCode", "articleSearch")
                .queryParam("key", API_KEY)
                .queryParam("title", title)
                .queryParam("affiliation", affiliation)
                .queryParam("keyword", keyword)
                .queryParam("author", author)
                .build()
                .encode()
                .toUri();
    }
}
