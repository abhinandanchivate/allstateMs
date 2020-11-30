package com.allstate.dbservices.repository;

import java.util.List;
import java.util.Optional;

import com.allstate.dbservices.model.JobApplicants;
import com.allstate.dbservices.model.JobApplicantsPostings;
import com.allstate.dbservices.model.JobPostings;

public interface JobApplicantsPostingsCustomRepository  {
	Optional<List<JobApplicantsPostings>> findAll();
	Optional<List<JobPostings>> findJobPostingsByApplicantId(Number jobPostingId);
	Optional<List<JobApplicants>> findJobApplicantsByJobPostingId(Number jobApplicantId);
	boolean deleteByIds(Number jobPostingId, Number jobApplicantId);
	boolean insertByIds(Number jobPostingId, Number jobApplicantId);
}
