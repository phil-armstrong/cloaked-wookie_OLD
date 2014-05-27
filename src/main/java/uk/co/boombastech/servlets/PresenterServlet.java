package uk.co.boombastech.servlets;

import uk.co.boombastech.presenters.Presenter;
import uk.co.boombastech.wiring.PathManager;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class PresenterServlet extends HttpServlet {

	private final PathManager pathManager;

	@Inject
	public PresenterServlet(PathManager pathManager) {
		this.pathManager = pathManager;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Presenter presenter = pathManager.getPresenterForRequest(request);

		if (presenter == null) {
			throw new RuntimeException("Can't find valid presenter");
		}

		presenter.execute(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
}