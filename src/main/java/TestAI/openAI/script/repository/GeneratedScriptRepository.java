package TestAI.openAI.script.repository;

import TestAI.openAI.script.entity.AbstractScriptInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneratedScriptRepository extends JpaRepository<AbstractScriptInfo, Long> {

}
