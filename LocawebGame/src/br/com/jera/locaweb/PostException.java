package br.com.jera.locaweb;

public class PostException extends Exception {

	private int statusCode;

	PostException(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	private static final long serialVersionUID = 1L;

}
