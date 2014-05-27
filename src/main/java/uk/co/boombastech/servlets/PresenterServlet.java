package uk.co.boombastech.servlets;

import uk.co.boombastech.exceptions.UnknownCommandException;
import uk.co.boombastech.exceptions.UnknownPresenterException;
import uk.co.boombastech.http.Command;
import uk.co.boombastech.http.GuicePathManager;
import uk.co.boombastech.http.Presenter;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class PresenterServlet extends HttpServlet {

	private final GuicePathManager pathManager;

	@Inject
	public PresenterServlet(GuicePathManager pathManager) {
		this.pathManager = pathManager;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Presenter presenter;
		try {
			presenter = pathManager.getPresenterForRequest(request);
		} catch (UnknownPresenterException e) {
			presenter = pathManager.getErrorPresenter();
		}

		presenter.execute(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Command command = pathManager.getCommandForRequest(request);
			command.execute(request, response);
		} catch (UnknownCommandException e) {
			pathManager.getErrorPresenter();
		}
	}
}