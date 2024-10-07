package com.immd3v.limsManager.repository;

import com.immd3v.limsManager.entity.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainerRepository extends JpaRepository<Container, Integer> {
    boolean existsByName(String name);
}
