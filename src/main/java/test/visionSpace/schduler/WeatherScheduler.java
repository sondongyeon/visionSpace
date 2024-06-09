package test.visionSpace.schduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import test.visionSpace.model.Response;
import test.visionSpace.model.WeatherInfo;
import test.visionSpace.service.WeatherService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class WeatherScheduler {
    @Autowired
    private WeatherService weatherService;

    private final RestTemplate restTemplate;

    public WeatherScheduler() {
        this.restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
        this.restTemplate.setMessageConverters(messageConverters);
    }

    private final String weatherApiUrl = "http://apis.data.go.kr/1360000/RoadWthrInfoService/getCctvStnRoadWthr\n" +
            "?serviceKey=%2FXsydQ660kCp%2FCsmOa1Xl5FyuI4VDYTWYRkCSvT8pW14SsLqsn4GD8nfFK8drBYiqlihfErSBmKAWfm6%2B91%2F1Q%3D%3D&numOfRows=10&pageNo=1\n" +
            "&eqmtId=0500C00001&hhCode=00\n"; // 기상청 API URL로 변경 필요

    @Scheduled(fixedRate = 60000) // 1분마다 실행
    public void fetchWeatherInfo() {
        try {
            Response response = restTemplate.getForObject(weatherApiUrl, Response.class);
            if (response != null && response.getBody() != null && response.getBody().getItems() != null) {
                List<WeatherInfo> weatherInfoList = response.getBody().getItems().getItemList();
                weatherInfoList.forEach(weatherService::addWeatherInfo);
            }
        } catch (Exception e) {
            e.printStackTrace(); // 실제 애플리케이션에서는 로깅을 사용하세요.
        }
    }
}
