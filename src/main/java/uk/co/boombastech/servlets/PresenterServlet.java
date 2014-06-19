package uk.co.boombastech.servlets;

import uk.co.boombastech.exceptions.UnknownCommandException;
import uk.co.boombastech.exceptions.UnknownPresenterException;
import uk.co.boombastech.exceptions.UnknownUriException;
import uk.co.boombastech.http.*;
import uk.co.boombastech.templating.Template;
import uk.co.boombastech.templating.TemplateManager;

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
	private final TemplateManager templateManager;

	@Inject
	public PresenterServlet(PathManager pathManager, TemplateManager templateManager) {
		this.pathManager = pathManager;
		this.templateManager = templateManager;
	}

	@Override
	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
		Presenter presenter;
		try {
			presenter = pathManager.getPresenterForRequest(httpServletRequest);
		} catch (UnknownPresenterException unknownPresenterException) {
			presenter = pathManager.getErrorPresenter();
		} catch (UnknownUriException unknownUriException) {
			presenter = pathManager.getErrorPresenter();
		}

		Request request = new Request() {};
		Response response = new Response() {};

		Template template = presenter.execute(request, response);
		templateManager.doTemplate(template, httpServletResponse);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Command command = pathManager.getCommandForRequest(request);
			command.execute(request, response);
		} catch (UnknownCommandException unknownPresenterException) {
			response.sendError(404);
		} catch (UnknownUriException unknownUriException) {
			response.sendError(404);
		}
	}
}