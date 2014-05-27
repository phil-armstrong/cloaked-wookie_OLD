package uk.co.boombastech.exceptions;

import uk.co.boombastech.http.UriFor;

public class UnknownCommandException extends Exception {
    private static final String EXCEPTION_MESSAGE = "There is no command class for UriInfo.%s";
    private UriFor uriFor;

    public UnknownCommandException(UriFor uriFor) {
        super(String.format(EXCEPTION_MESSAGE, uriFor));
    }
}