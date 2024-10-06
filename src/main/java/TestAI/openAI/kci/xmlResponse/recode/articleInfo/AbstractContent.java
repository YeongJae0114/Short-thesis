package TestAI.openAI.kci.xmlResponse.recode.articleInfo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class AbstractContent {

    // Getters and setters
    @XmlAttribute(name = "lang")
    private String lang;  // lang 속성

    @XmlValue
    private String content;  // CDATA 내용 (abstract 내용)

}