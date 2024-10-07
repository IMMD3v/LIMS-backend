package com.immd3v.limsManager.repository;

import com.immd3v.limsManager.entity.Liquid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LiquidRepository extends JpaRepository<Liquid, Integer> {
    Optional<Liquid> findByDescription (String description);
}
