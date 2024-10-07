package TestAI.openAI.script.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Author {
    @Id
    private Long id;
    private String name;
    private String affiliation;

    @ManyToOne
    private AbstractScriptInfo abstractScriptInfo;
}
