package com.c4u.course4you.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassedQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  double note;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createDate;
    @OneToOne
    private Quiz quiz;
    @ManyToOne
    @JoinColumn(name = "person")
    @JsonBackReference
    private  Person person;
}
