package test.visionSpace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.visionSpace.model.WeatherInfo;
import test.visionSpace.repository.WeatherRepository;

import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    public List<WeatherInfo> getWeatherInfo() {
        return weatherRepository.findAll();
    }

    public void addWeatherInfo(WeatherInfo weatherInfo) {
        weatherRepository.save(weatherInfo);
    }

    public void deleteWeatherInfo(Long id) {
        weatherRepository.deleteById(id);
    }
}
