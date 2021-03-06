package com.bridgelabz.iplleagueanalysis;

public class IPLAnalyserException extends Exception {
	private static final long serialVersionUID = 1L;

	enum ExceptionType {
		BATTING_FILE_PROBLEM, BOWLING_FILE_PROBLEM, SOME_FILE_ISSUE,NO_BATTING_DATA, NO_BOWLING_DATA
	}

	ExceptionType type;
	String exceptionType;

	public IPLAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}

	public IPLAnalyserException(String message, ExceptionType type, Throwable cause) {
		super(message, cause);
		this.type = type;
	}

	public IPLAnalyserException(String message, String exceptionType) {
		super(message);
		this.exceptionType = exceptionType;
	}
}