package com.cooksys.springassessment.exception;

public class SelfRelationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3934175788880723622L;

	public SelfRelationException(Long id) {
		super("[" + id + "] cannot be related to itself!");
	}
}
