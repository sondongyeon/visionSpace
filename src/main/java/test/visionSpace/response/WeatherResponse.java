package test.visionSpace.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class WeatherResponse {
    private WeatherBody body;

    public WeatherBody getBody() {
        return body;
    }

    public void setBody(WeatherBody body) {
        this.body = body;
    }
}

class WeatherBody {
    private List<WeatherItem> items;

    public List<WeatherItem> getItems() {
        return items;
    }

    public void setItems(List<WeatherItem> items) {
        this.items = items;
    }
}

@Getter @Setter
class WeatherItem {
    private String baseDate;
    private String baseTime;
    private String weatherNm;
    private String cctvId;
    private String cctvNm;

}