package TestAI.openAI.kci.xmlResponse.recode.articleInfo;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ArticleTitle {

    @XmlAttribute(name = "lang")
    private String lang;  // "original", "foreign", "english" 등

    @XmlValue
    private String content;  // 제목 내용
}
