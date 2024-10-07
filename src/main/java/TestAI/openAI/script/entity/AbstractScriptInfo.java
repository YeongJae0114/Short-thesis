package TestAI.openAI.script.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AbstractScriptInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String articleId;
    private String articleTitle;
    private String shortFormScript;
    private String url;
    private int pubYear;

    @OneToMany
    private List<Author> authors;
}
