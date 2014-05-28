package uk.co.boombastech.exceptions;

import uk.co.boombastech.http.UriFor;

public class UnknownPresenterException extends Exception {
    private static final String EXCEPTION_MESSAGE = "There is no presenter class for UriInfo.%s";
    private UriFor uriFor;

    public UnknownPresenterException(UriFor uriFor) {
        super(String.format(EXCEPTION_MESSAGE, uriFor));
    }
}
