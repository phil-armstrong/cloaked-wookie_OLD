package uk.co.boombastech.servlets;

import com.google.common.collect.Maps;
import uk.co.boombastech.presenters.AbstractPresenter;
import uk.co.boombastech.uris.UriFor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public abstract class HtmlServlet extends HttpServlet {
	protected final Map<UriFor, Class<? extends AbstractPresenter>> presenters;

	public HtmlServlet() {
		presenters = Maps.newHashMap();
		registerPresenters();
	}

	public abstract void registerPresenters();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AbstractPresenter presenter = null;
		for (Map.Entry<UriFor, Class<? extends AbstractPresenter>> presenterLink : presenters.entrySet()) {
			if (presenterLink.getKey().matches(req.getRequestURI())) {
				Request request = WiringDefinition.getGuiceInjector().getInstance(Request.class);
				Response response = WiringDefinition.getGuiceInjector().getInstance(Response.class);

				presenter = WiringDefinition.getGuiceInjector().getInstance(presenterLink.getValue());
				presenter.execute(request.with(req), response.with(resp));
			}
		}
		if (presenter == null) {
			throw new RuntimeException("Can't find valid presenter");
		}
	}
}