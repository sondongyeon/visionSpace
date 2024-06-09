package test.visionSpace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import test.visionSpace.response.WeatherResponse;

@Controller
@RequestMapping("/weather")
public class WeatherController {
    private static final String API_URL = "http://apis.data.go.kr/1360000/RoadWthrInfoService/getStdNodeLinkRoadWw?serviceKey=%2FXsydQ660kCp%2FCsmOa1Xl5FyuI4VDYTWYRkCSvT8pW14SsLqsn4GD8nfFK8drBYiqlihfErSBmKAWfm6%2B91%2F1Q%3D%3D&numOfRows=10&pageNo=1&stdLinkId=2370012300&hhCode=00";

    @GetMapping("/data")
    public String getWeather(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        WeatherResponse response = restTemplate.getForObject(API_URL, WeatherResponse.class);
        model.addAttribute("weather", response.getBody());
        return "weather";
    }
}
