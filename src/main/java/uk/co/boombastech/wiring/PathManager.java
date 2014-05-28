package uk.co.boombastech.wiring;

import com.google.inject.Injector;
import uk.co.boombastech.presenters.InvalidUrlException;
import uk.co.boombastech.presenters.Presenter;
import uk.co.boombastech.uris.UriFor;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;

@Singleton
public class PathManager {

	private final Injector injector;

	@Inject
	public PathManager(Injector injector) {
		this.injector = injector;
	}

	public Presenter getPresenterForRequest(HttpServletRequest request) throws InvalidUrlException {
		UriFor uriFor = UriFor.getUriForFromRequest(request);
		return injector.getInstance(uriFor.getPresenterClass());
	}
}