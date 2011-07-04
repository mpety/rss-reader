package com.mpety.rssreader.common.exception;

public class RssException extends Exception {
	
	private static final long serialVersionUID = -8785158060240427309L;
	
	public static final int WRONG_URL = 1;
	public static final int IO_ERROR = 2;
	private int errorCode;

	public RssException(String message, int errorCode, Throwable cause){
		super(message, cause);
		
		this.errorCode = errorCode;
	}
	
	public RssException(String message, int errorCode){
		this(message, errorCode, null);
	}

	public int getErrorCode() {
		return errorCode;
	}

	@Override
	public String toString() {
		return "RssException [errorCode=" + errorCode + ", getMessage()="
				+ getMessage() + ", getCause()=" + getCause() + "]";
	}

}
