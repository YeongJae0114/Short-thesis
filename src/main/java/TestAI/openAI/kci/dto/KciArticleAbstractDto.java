package TestAI.openAI.kci.dto;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KciArticleAbstractDto {
    private String articleId;
    private String articleTitle;
    private List<String> authors;
    private String abstractCt;
    private String shortFormScript;
    private String url;
    private int pubYear;

}
