package TestAI.openAI.kci.xmlResponse;

import TestAI.openAI.kci.xmlResponse.recode.ArticleInfo;
import TestAI.openAI.kci.xmlResponse.recode.JournalInfo;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Setter;

@Setter
public class Record {

    private JournalInfo journalInfo;
    private ArticleInfo articleInfo;

    @XmlElement(name = "journalInfo")
    public JournalInfo getJournalInfo() {
        return journalInfo;
    }

    @XmlElement(name = "articleInfo")
    public ArticleInfo getArticleInfo() {
        return articleInfo;
    }

}