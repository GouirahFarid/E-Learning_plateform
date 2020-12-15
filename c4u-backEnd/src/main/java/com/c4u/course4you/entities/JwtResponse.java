package com.c4u.course4you.entities;

import lombok.Data;

import java.util.List;
@Data
public class JwtResponse {
    private String token;
    private final String type = "Bearer";
    private Long id;
    private String username;
    private List<String> roles;
    private List<Course> courses;

    public JwtResponse(String token, Long id, String username, List<String> roles, List<Course> courses) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.courses = courses;
    }
}
