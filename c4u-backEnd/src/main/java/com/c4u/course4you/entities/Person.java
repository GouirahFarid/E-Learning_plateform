package com.c4u.course4you.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public  class Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotNull(message = "username cannot be null")
    private  String username;
    @Column(unique = true)
    @NotNull(message = "mail cannot be null")
    @Email(message = "Email should be valid")
    private String mail;
    @NotNull(message = "password cannot be null")
    @Size(min = 8,message = "Password must be great than 8")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotNull(message = "First name cannot be null")
    private String firstName;
    @NotNull(message = "Second name cannot be null")
    private String secondName;
    private  String biography;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createDate;
    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    private Date updateDate;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles=new ArrayList<>();
    @ManyToMany(mappedBy = "persons",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Course> courses=new ArrayList<>();
    @OneToMany(mappedBy = "person")
    @JsonManagedReference
    private  List<PassedQuiz> passedQuizs=new ArrayList<>();
}
