package TestAI.openAI.script.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class AbstractScriptInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String articleId;
    private String articleTitle;
    private String shortFormScript;
    private String url;
    private int pubYear;

    @OneToMany(mappedBy = "abstractScriptInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Author> authors = new ArrayList<>();
}
