package com.works.team.repositories;

import com.works.team.entites.Class;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassRepository extends JpaRepository<Class, Long> {
    Optional<Class> findByNameEqualsAndSidEquals(String name, Long sid);
}