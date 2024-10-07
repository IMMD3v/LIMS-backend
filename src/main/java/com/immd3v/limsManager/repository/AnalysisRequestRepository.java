package com.immd3v.limsManager.repository;

import com.immd3v.limsManager.entity.AnalysisRequest;
import com.immd3v.limsManager.entity.Liquid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnalysisRequestRepository extends JpaRepository <AnalysisRequest, Integer> {
    List<AnalysisRequest> findAllByLiquid (Liquid liquid);
}
