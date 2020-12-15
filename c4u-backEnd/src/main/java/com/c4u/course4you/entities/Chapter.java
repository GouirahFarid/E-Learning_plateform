package com.c4u.course4you.entities;

import com.fasterxml.jackson.annotation.*;
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
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String title;
    private String description;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @CreationTimestamp
    private Date createDate;
    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    @Column(nullable = false, updatable = false)
    private Date updateDate;
    @ManyToMany
    @JoinColumn(name = "courses")
    @JsonBackReference
    private List<Course> courses=new ArrayList<>();
    @OneToMany(mappedBy = "chapter")
    @JsonManagedReference
    private List<SubChapter> subChapters=new ArrayList<>();
}
