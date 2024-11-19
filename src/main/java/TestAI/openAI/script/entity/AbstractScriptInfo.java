package TestAI.openAI.script.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class AbstractScriptInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String articleId;
    private String articleTitle;
    @Column(columnDefinition = "TEXT")
    private String shortFormScript;
    private String url;
    private int pubYear;
    private String videoUrl;

}
