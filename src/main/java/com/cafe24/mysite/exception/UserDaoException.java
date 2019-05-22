package com.cafe24.mysite.exception;

public class UserDaoException extends RuntimeException {

	private static final long serialVersionUID = -8484040749371433328L;

	public UserDaoException() {
		super("UserdaoException");
	}

	public UserDaoException(String message) {
		super(message);
	}
}
