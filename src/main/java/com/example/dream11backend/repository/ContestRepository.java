package com.example.dream11backend.repository;

import com.example.dream11backend.entity.Contest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Long> {
    Page<Contest> findByStatus(String status, Pageable pageable);
    Page<Contest> findByCategory(String category, Pageable pageable);
    Page<Contest> findByEntryFeeBetween(Double minFee, Double maxFee, Pageable pageable);
    List<Contest> findByParticipants_Id(Long userId);
}

