package uk.co.boombastech.staticcontent;

import uk.co.boombastech.properties.PropertiesProvider;
import uk.co.boombastech.system.FileHandler;
import uk.co.boombastech.utils.guava.GuavaFilesWrapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@Singleton
public class HtmlServlet extends HttpServlet {

	private final PropertiesProvider propertiesProvider;
	private final FileHandler fileHandler;
	private final GuavaFilesWrapper guavaWrapper;

	@Inject
	public HtmlServlet(PropertiesProvider propertiesProvider, FileHandler fileHandler, GuavaFilesWrapper guavaWrapper) {
		this.propertiesProvider = propertiesProvider;
		this.fileHandler = fileHandler;
		this.guavaWrapper = guavaWrapper;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		File file = fileHandler.getFile("web/html", requestURI);

		if (!file.exists()) {
			response.setStatus(404);
			return;
		}

		PrintWriter writer = response.getWriter();
		writer.append(guavaWrapper.toString(file));
		writer.flush();
	}
}