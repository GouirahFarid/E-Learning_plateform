package com.c4u.course4you.entities.form;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseForm {
    private  String title;
    private String description;
    private List<Long> categoriesId=new ArrayList<>();

}
