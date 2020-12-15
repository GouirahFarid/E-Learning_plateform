package com.c4u.course4you.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.persistence.*;

@ConfigurationProperties(prefix = "file")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String resourceType;
    private  String uploadDir;
    @OneToOne
    @JoinColumn(name = "course")
    @JsonBackReference
    private Course course;
    @ManyToOne
    @JoinColumn(name = "subChapter")
    @JsonBackReference
    private SubChapter subChapter;
}
