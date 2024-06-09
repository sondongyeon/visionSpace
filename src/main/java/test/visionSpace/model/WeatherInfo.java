package test.visionSpace.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table
@NoArgsConstructor
@JacksonXmlRootElement(localName = "item")
public class WeatherInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JacksonXmlProperty(localName = "baseDate")
    private String baseDate;

    @JacksonXmlProperty(localName = "baseTime")
    private String baseTime;

    @JacksonXmlProperty(localName = "weatherNm")
    private String weatherNm;

}
