package TestAI.openAI.kci.xmlResponse.recode.articleInfo;

import jakarta.xml.bind.annotation.*;
import lombok.Data;


@Data
@XmlAccessorType(XmlAccessType.FIELD)

public class Author {

    @XmlValue
    private String name;  // 한글 이름

    @XmlAttribute(name = "english")
    private String englishName;  // 영어 이름
}
