package TestAI.openAI.script.repository;

import TestAI.openAI.script.entity.AbstractScriptInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeneratedScriptRepository extends JpaRepository<AbstractScriptInfo, Long> {
    default AbstractScriptInfo findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Entity not found with ID: " + id));
    }
    Optional<AbstractScriptInfo> findByArticleId(String articleId);
}
