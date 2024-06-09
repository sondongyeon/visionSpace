package test.visionSpace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.visionSpace.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
