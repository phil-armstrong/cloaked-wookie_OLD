package uk.co.boombastech.uris;

import uk.co.boombastech.presenters.Presenter;
import uk.co.boombastech.presenters.PrototypePresenter;

import javax.servlet.http.HttpServletRequest;

public enum UriFor {
	homepage("/", PrototypePresenter.class)
	;

	private final String url;
	private final Class<? extends Presenter> presenterClass;

	UriFor(String url, Class<? extends Presenter> presenterClass) {
		this.url = url;
		this.presenterClass = presenterClass;
	}

	public String getUrl() {
		return url;
	}

	public Class<? extends Presenter> getPresenterClass() {
		return presenterClass;
	}

	public static UriFor getUriForFromRequest(HttpServletRequest request) {
		for (UriFor uriFor : UriFor.values()) {
			if (uriFor.url.equalsIgnoreCase(request.getRequestURI())) {
				return uriFor;
			}
		}
		return null;
	}
}