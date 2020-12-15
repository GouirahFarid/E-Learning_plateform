package com.c4u.course4you.repositories;
import com.c4u.course4you.entities.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionOptionRepository extends JpaRepository<QuestionOption,Long> {
}
