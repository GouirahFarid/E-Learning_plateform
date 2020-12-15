package com.c4u.course4you.repositories;

import com.c4u.course4you.entities.SubChapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubChapterRepository  extends JpaRepository<SubChapter,Long> {
    Optional<SubChapter> findByTitleContaining(String title);
}
