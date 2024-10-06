package TestAI.openAI.kci.xmlResponse.recode.articleInfo;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;


import java.util.List;

@Data
public class AbstractGroup {

    private List<AbstractContent> abstracts;

    @XmlElement(name = "abstract")
    public List<AbstractContent> getAbstracts() {
        return abstracts;
    }

}