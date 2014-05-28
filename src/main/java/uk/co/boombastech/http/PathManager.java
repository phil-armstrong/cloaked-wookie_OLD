package uk.co.boombastech.http;

import uk.co.boombastech.exceptions.UnknownCommandException;
import uk.co.boombastech.exceptions.UnknownPresenterException;
import uk.co.boombastech.exceptions.UnknownUriException;

import javax.servlet.http.HttpServletRequest;

public interface PathManager {
    Presenter getPresenterForRequest(HttpServletRequest request) throws UnknownPresenterException, UnknownUriException;
    Command getCommandForRequest(HttpServletRequest request) throws UnknownCommandException, UnknownUriException;
	Presenter getErrorPresenter();
}