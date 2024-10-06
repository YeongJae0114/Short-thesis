package TestAI.openAI.kci.abstractInfo;

import lombok.Data;

import java.util.List;
@Data
public class KciArticleAbstract {
    private String articleId;
    private String articleTitle;
    private List<String> authors;
    private String abstractCt;
    private String url;
    private int pubYear;
}
