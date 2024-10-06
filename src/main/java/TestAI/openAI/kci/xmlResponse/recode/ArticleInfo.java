package TestAI.openAI.kci.xmlResponse.recode;

import TestAI.openAI.kci.xmlResponse.recode.articleInfo.AbstractGroup;
import TestAI.openAI.kci.xmlResponse.recode.articleInfo.ArticleTitle;
import TestAI.openAI.kci.xmlResponse.recode.articleInfo.AuthorGroup;
import TestAI.openAI.kci.xmlResponse.recode.articleInfo.TitleGroup;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
public class ArticleInfo {

    private String articleId;
    private String articleCategories;
    private String articleRegularity;
    private String url;

    private AbstractGroup abstractGroup;
    private TitleGroup titleGroup;
    private AuthorGroup authorGroup;


    @XmlAttribute(name = "article-id")
    public String getArticleId() {
        return articleId;
    }

    @XmlElement(name = "article-categories")
    public String getArticleCategories() {
        return articleCategories;
    }

    @XmlElement(name = "article-regularity")
    public String getArticleRegularity() {
        return articleRegularity;
    }

    @XmlElement(name = "url")
    public String getUrl() {
        return url;
    }

    @XmlElement(name = "abstract-group")
    public AbstractGroup getAbstractGroup() {
        return abstractGroup;
    }
    @XmlElement(name = "title-group")
    public TitleGroup getTitleGroup() {
        return titleGroup;
    }

    @XmlElement(name = "author-group")
    public AuthorGroup getAuthorGroup() {
        return authorGroup;
    }

    public String getOriginalTitle() {
        if (titleGroup != null && titleGroup.getArticleTitles() != null) {
            for (ArticleTitle title : titleGroup.getArticleTitles()) {
                if ("original".equals(title.getLang())) {
                    return title.getContent();  // original 제목을 찾으면 반환
                }
            }
        }
        return null;  // original 제목이 없으면 null 반환
    }

}