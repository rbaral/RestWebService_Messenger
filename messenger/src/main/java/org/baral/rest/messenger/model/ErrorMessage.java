package org.baral.rest.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

	private String error;
	private int errorCode;
	private String errorDoc;
	
	public ErrorMessage(){
		
	}

	public ErrorMessage(String error, int code, String doc) {
		this.error = error;
		this.errorCode = code;
		this.errorDoc = doc;
	}
	
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDoc() {
		return errorDoc;
	}
	public void setErrorDoc(String errorDoc) {
		this.errorDoc = errorDoc;
	}

}
