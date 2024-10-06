package TestAI.openAI.kci.xmlResponse.recode.articleInfo;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)

public class TitleGroup {

    @XmlElement(name = "article-title")
    private List<ArticleTitle> articleTitles;
}
