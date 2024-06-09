package test.visionSpace.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Setter @Getter
@Table
@NoArgsConstructor
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentId;

    private String title;

    private String description;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();
}
