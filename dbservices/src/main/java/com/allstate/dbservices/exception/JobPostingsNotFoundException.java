package com.allstate.dbservices.exception;

public class JobPostingsNotFoundException extends RuntimeException{
	public JobPostingsNotFoundException(Integer id) {
		super("could not find job posting with Id" + id);
	}
}
