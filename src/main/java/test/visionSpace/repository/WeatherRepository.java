package test.visionSpace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.visionSpace.model.WeatherInfo;

public interface WeatherRepository extends JpaRepository<WeatherInfo, Long> {
}
