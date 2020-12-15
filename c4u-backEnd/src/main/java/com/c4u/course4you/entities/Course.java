package com.c4u.course4you.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String title;
    private String description;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createDate;
    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    @Column(nullable = false, updatable = false)
    private Date updateDate;
    @ManyToMany(mappedBy = "courses",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Chapter> chapters=new ArrayList<>();
    @ManyToMany
    @JoinColumn(name = "categories")
    @JsonBackReference
    private List<Category> categories=new ArrayList<>();
    @ManyToMany
    @JoinColumn(name = "persons")
     @JsonBackReference
    private List<Person> persons=new ArrayList<>();
    @OneToOne(mappedBy = "course",fetch = FetchType.LAZY)
    @JsonManagedReference
    private CourseResource courseResource;
    @OneToMany(mappedBy = "course")
    @JsonManagedReference
    private  List<Quiz> quizzes=new ArrayList<>();
}
