package com.allstate.dbservices.repository;

import java.util.List;

import com.allstate.dbservices.model.JobPostings;

public interface JobPostingsCustomRepository {
	List<JobPostings> findJobByStatus(String status);
	List<JobPostings> findJobByDesc(String description);
}
