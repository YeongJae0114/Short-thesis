package TestAI.openAI.kci.dto;

import lombok.Data;

import java.util.List;
@Data
public class KciArticleAbstractDto {
    private String articleId;
    private String articleTitle;
    private List<String> authors;
    private String abstractCt;
    private String url;
    private int pubYear;
}
