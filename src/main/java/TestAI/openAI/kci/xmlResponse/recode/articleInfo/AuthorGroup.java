package TestAI.openAI.kci.xmlResponse.recode.articleInfo;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;


import java.util.List;

@Data
public class AuthorGroup {
    private List<Author> Authors;

    @XmlElement(name = "author")
    public List<Author> getAuthors() {
        return Authors;
    }
}
