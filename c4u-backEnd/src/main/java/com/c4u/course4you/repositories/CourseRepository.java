package com.c4u.course4you.repositories;

import com.c4u.course4you.entities.Course;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findAllByPersons_Id(Long userID);
    Optional<Course> findByIdAndPersons_Id(Long courseId,Long personId);
    List<Course> findAllByCategories_Title(String title);
    List<Course> findByCategories_Id(Long categoryId);
    List<Course> findByChapters_Id(Long chapterId);
    Optional<Course> findByTitleContaining(String title);
}
