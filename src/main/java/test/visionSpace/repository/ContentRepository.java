package test.visionSpace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.visionSpace.model.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, String> {
}
