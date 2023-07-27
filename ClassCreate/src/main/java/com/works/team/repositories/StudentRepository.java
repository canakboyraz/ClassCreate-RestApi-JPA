package com.works.team.repositories;

import com.works.team.entites.Student;
import com.works.team.entites.projections.IStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmailEqualsIgnoreCase(String email);

    Optional<Student> findByEmailEqualsIgnoreCaseAndPasswordEquals(String email, String password);

    @Query(value = "select s.name,s.surname,s.age from class inner join student s on class.sid = s.sid where class.name = ?1 order by s.age limit ?2 offset ?3", nativeQuery = true)
    List<IStudent> getStudentsBy(String className,int limit,int offset);



}