package TestAI.openAI.kci.xmlResponse;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Setter;

@Setter
public class Result {

    private int total;

    @XmlElement(name = "total")
    public int getTotal() {
        return total;
    }

}