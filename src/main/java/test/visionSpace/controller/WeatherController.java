package test.visionSpace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import test.visionSpace.model.WeatherInfo;
import test.visionSpace.service.WeatherService;

import java.util.List;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/list")
    @ResponseBody
    public List<WeatherInfo> getWeatherInfo() {
        return weatherService.getWeatherInfo();
    }

    @PostMapping("/add")
    public void addWeatherInfo(@RequestBody WeatherInfo weatherInfo) {
        weatherService.addWeatherInfo(weatherInfo);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteWeatherInfo(@PathVariable Long id) {
        weatherService.deleteWeatherInfo(id);
    }
}
