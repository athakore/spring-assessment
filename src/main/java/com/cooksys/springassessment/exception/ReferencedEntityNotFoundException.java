package com.cooksys.springassessment.exception;

import java.io.Serializable;

public class ReferencedEntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -1439958071174318831L;

	public ReferencedEntityNotFoundException(Class<?> entityClass, Serializable id) {
		super("Cannot find [" + entityClass.getSimpleName() + "] with ID of [" + id + "]");
	}
}
