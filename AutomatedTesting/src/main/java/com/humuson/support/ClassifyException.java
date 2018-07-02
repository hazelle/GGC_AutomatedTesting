package com.humuson.support;

public class ClassifyException extends CustomLogging {
	private Exception e;
	
	
	public ClassifyException(Exception e) {
		this.e = e;
	}
	
	public String toString() {
		return this.e.getMessage();
	}
	
	
}
