package com.c4u.course4you.repositories;

import com.c4u.course4you.entities.CourseResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseResourceRepository extends JpaRepository<CourseResource,Long > {
    CourseResource findByFileName(String fileName);
}
