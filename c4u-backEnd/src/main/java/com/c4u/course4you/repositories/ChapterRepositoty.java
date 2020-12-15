package com.c4u.course4you.repositories;

import com.c4u.course4you.entities.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepositoty extends JpaRepository<Chapter,Long> {
}
