package com.c4u.course4you.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private double mark;
    @OneToMany(mappedBy = "question")
    @JsonManagedReference
    private List<QuestionOption> questionOptions=new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "quiz")
    @JsonBackReference
    private  Quiz quiz;
}
