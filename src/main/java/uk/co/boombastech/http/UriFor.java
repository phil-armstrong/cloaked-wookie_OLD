package uk.co.boombastech.http;

import uk.co.boombastech.exceptions.UnknownCommandException;
import uk.co.boombastech.exceptions.UnknownPresenterException;
import uk.co.boombastech.exceptions.UnknownUriException;
import uk.co.boombastech.presenters.PrototypePresenter;

import javax.servlet.http.HttpServletRequest;

public enum UriFor {
	homepage("/", PrototypePresenter.class),
    login("account/login", null, null),
	;

	private final String url;
	private final Class<? extends Presenter> presenterClass;
    private final Class<? extends Command> commandClass;

    UriFor(String url, Class<? extends Presenter> presenterClass) {
		this.url = url;
		this.presenterClass = presenterClass;
        this.commandClass = null;
	}

    UriFor(String url, Class<? extends Presenter> presenterClass, Class<? extends Command> commandClass) {
        this.url = url;
        this.presenterClass = presenterClass;
        this.commandClass = commandClass;
    }

	public String getUrl() {
		return url;
	}

    public Class<? extends Command> getCommandClass() throws UnknownCommandException {
        if (commandClass == null)
            throw new UnknownCommandException(this);
        return commandClass;
    }

    public Class<? extends Presenter> getPresenterClass() throws UnknownPresenterException {
        if (presenterClass == null) {
            throw new UnknownPresenterException(this);
        }
		return presenterClass;
	}

	public static UriFor getUriForFromRequest(HttpServletRequest request) throws UnknownUriException {
		for (UriFor uriFor : UriFor.values()) {
			if (uriFor.url.equalsIgnoreCase(request.getRequestURI())) {
				return uriFor;
			}
		}
		throw new UnknownUriException(request.getRequestURI());
	}
}