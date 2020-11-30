package com.allstate.dbservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allstate.dbservices.model.Position;

@Repository
public interface JobPostingRepository extends JpaRepository<Position, Integer> {

}
