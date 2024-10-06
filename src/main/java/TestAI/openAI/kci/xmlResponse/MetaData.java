package TestAI.openAI.kci.xmlResponse;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Setter;

@Setter
@XmlRootElement(name = "MetaData")
public class MetaData {

    private OutputData outputData;

    @XmlElement(name = "outputData")
    public OutputData getOutputData() {
        return outputData;
    }

}