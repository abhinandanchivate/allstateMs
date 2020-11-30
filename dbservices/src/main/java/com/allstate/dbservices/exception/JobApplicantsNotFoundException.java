package com.allstate.dbservices.exception;

public class JobApplicantsNotFoundException extends RuntimeException{
	public JobApplicantsNotFoundException(Integer id) {
		super("could not find job applicant with Id" + id);
	}
}
