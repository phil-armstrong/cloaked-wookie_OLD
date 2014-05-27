package uk.co.boombastech.http;

import com.google.inject.Injector;
import uk.co.boombastech.exceptions.UnknownCommandException;
import uk.co.boombastech.exceptions.UnknownPresenterException;
import uk.co.boombastech.presenters.ErrorPagePresenter;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;

@Singleton
public class GuicePathManager implements PathManager {

	private final Injector injector;

    @Inject
	public GuicePathManager(Injector injector) {
		this.injector = injector;
	}

    @Override
	public Presenter getPresenterForRequest(HttpServletRequest request) throws UnknownPresenterException {
		UriFor uriFor = UriFor.getUriForFromRequest(request);
		return injector.getInstance(uriFor.getPresenterClass());
	}

    @Override
    public Command getCommandForRequest(HttpServletRequest request) throws UnknownCommandException {
        UriFor uriFor = UriFor.getUriForFromRequest(request);
        return injector.getInstance(uriFor.getCommandClass());
    }

    public Presenter getErrorPresenter() {
        return injector.getInstance(ErrorPagePresenter.class);
    }
}