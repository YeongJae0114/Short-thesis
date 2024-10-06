package TestAI.openAI.kci.xmlResponse.recode;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Setter;

@Setter
public class JournalInfo {

    private String journalName;
    private String publisherName;
    private int pubYear;
    private int pubMon;
    private int volume;
    private int issue;

    @XmlElement(name = "journal-name")
    public String getJournalName() {
        return journalName;
    }

    @XmlElement(name = "publisher-name")
    public String getPublisherName() {
        return publisherName;
    }

    @XmlElement(name = "pub-year")
    public int getPubYear() {
        return pubYear;
    }

    @XmlElement(name = "pub-mon")
    public int getPubMon() {
        return pubMon;
    }

    @XmlElement(name = "volume")
    public int getVolume() {
        return volume;
    }

    @XmlElement(name = "issue")
    public int getIssue() {
        return issue;
    }

}