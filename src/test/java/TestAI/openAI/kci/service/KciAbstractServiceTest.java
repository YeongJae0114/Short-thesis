package TestAI.openAI.kci.service;

import TestAI.openAI.kci.dto.KciArticleAbstractDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor
class KciAbstractServiceTest {
    private final KciAbstractService kciAbstractService;

    @Test
    public void getAllAbstractTest() throws Exception{
        //given
        String xmlResponse = "<MetaData><outputData><records><record><articleInfo article-id=\"ART002813129\"><title-group><article-title lang=\"original\"><![CDATA[ 드론을 이용한 하천공간정보 획득의 효율적 방안 ]]></article-title></title-group><author-group><author>이종석(한밭대학교)</author></author-group><abstract-group><abstract lang=\"original\"><![CDATA[ 본 연구는 지방하천의 하류구간을 대상으로... ]]></abstract></abstract-group><url>http://testurl.com</url></articleInfo><journalInfo><pub-year>2022</pub-year></journalInfo></record></records></outputData></MetaData>";

        List<KciArticleAbstractDto> allAbstract = kciAbstractService.getAllAbstract("드론", "한밭대학교");

        //when
     
        //then
    }
    
}