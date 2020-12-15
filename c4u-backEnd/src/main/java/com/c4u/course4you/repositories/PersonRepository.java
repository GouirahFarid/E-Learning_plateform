package com.c4u.course4you.repositories;

import com.c4u.course4you.entities.Course;
import com.c4u.course4you.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    Optional<Person> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByMail(String mail);
    List<Person> findByRoles_RoleNameContaining(String roleName);
    Optional<Person> findByPassword(String password);
}
