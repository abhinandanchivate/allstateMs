package com.allstate.dbservices.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.allstate.dbservices.model.JobPostings;

@Repository
@Transactional(readOnly = true)
public class JobPostingsCustomRepositoryImpl implements JobPostingsCustomRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<JobPostings> findJobByStatus(String status) {
		Query query = entityManager.createNativeQuery("SELECT jp.* FROM hrms.job_postings as jp " +
                "WHERE jp.application_status = ?", JobPostings.class);
        query.setParameter(1, status);
        return query.getResultList();
	}

	@Override
	public List<JobPostings> findJobByDesc(String description) {
		Query query = entityManager.createNativeQuery("SELECT jp.* FROM hrms.job_postings as jp "+
				"WHERE jp.job_description LIKE ?", JobPostings.class);
		query.setParameter(1, "%" + description + "%");
		return query.getResultList();
	}

}
