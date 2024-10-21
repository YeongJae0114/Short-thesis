package TestAI.openAI.script.repository;

import TestAI.openAI.script.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByArticleId(String articleId);

}
