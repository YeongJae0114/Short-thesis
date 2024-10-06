package TestAI.openAI.kci.xmlResponse;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Setter;

import java.util.List;


@Setter
public class OutputData {

    private Result result;
    private List<Record> records;

    @XmlElement(name = "result")
    public Result getResult() {
        return result;
    }

    @XmlElement(name = "record")
    public List<Record> getRecords() {
        return records;
    }

}