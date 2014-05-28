package uk.co.boombastech.exceptions;

public class UnknownUriException extends Exception {
	private static final String EXCEPTION_MESSAGE = "The uri: '%s' does not have a UriFor associated with it";

	public UnknownUriException(String url) {
		super(String.format(EXCEPTION_MESSAGE, url));
	}
}