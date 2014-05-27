package uk.co.boombastech.http;

import uk.co.boombastech.exceptions.UnknownCommandException;
import uk.co.boombastech.exceptions.UnknownPresenterException;

import javax.servlet.http.HttpServletRequest;

public interface PathManager {
    Presenter getPresenterForRequest(HttpServletRequest request) throws UnknownPresenterException;
    Command getCommandForRequest(HttpServletRequest request) throws UnknownCommandException;
}