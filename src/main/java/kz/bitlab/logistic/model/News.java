package kz.bitlab.logistic.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class News extends BaseEntity {

    private String title;
    private String topic;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String image;
}
