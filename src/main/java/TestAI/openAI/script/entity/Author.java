package TestAI.openAI.script.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String affiliation;

    @ManyToOne
    @JoinColumn(name = "abstract_script_info_id")
    private AbstractScriptInfo abstractScriptInfo;
}
