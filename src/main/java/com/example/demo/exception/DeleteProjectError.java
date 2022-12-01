package com.example.demo.exception;


public class DeleteProjectError extends RuntimeException {
	public DeleteProjectError(String msg) {
		super(msg);
	}
}
