package com.allstate.dbservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allstate.dbservices.model.JobApplicants;

@Repository
public interface JobApplicantsRepository extends JpaRepository<JobApplicants, Integer> {
}
