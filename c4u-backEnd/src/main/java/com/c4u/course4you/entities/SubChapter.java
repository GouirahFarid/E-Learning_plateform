package com.c4u.course4you.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubChapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String title;
    private  String content;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @CreationTimestamp
    private Date createDate;
    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    @Column(nullable = false, updatable = false)
    private Date updateDate;
    @ManyToOne
    @JoinColumn(name = "chapter")
    @JsonBackReference
    private Chapter chapter;
    @OneToMany(mappedBy = "subChapter")
    @JsonManagedReference
    private List<CourseResource> courseResources =new ArrayList<>();

}
