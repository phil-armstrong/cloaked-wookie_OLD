package uk.co.boombastech.servlets;

import uk.co.boombastech.properties.PropertiesProvider;
import uk.co.boombastech.properties.Property;
import uk.co.boombastech.system.FileHandler;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import static uk.co.boombastech.properties.Property.javascriptPath;

@Singleton
public class JavascriptServlet extends HttpServlet {

	private final PropertiesProvider propertiesProvider;
	private final FileHandler fileHandler;

	@Inject
	public JavascriptServlet(PropertiesProvider propertiesProvider, FileHandler fileHandler) {
		this.propertiesProvider = propertiesProvider;
		this.fileHandler = fileHandler;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String fileName = getFilenameFromURI(requestURI);
		try {
			File file = fileHandler.getFile(propertiesProvider.getProperty(javascriptPath), fileName);
			PrintWriter writer = response.getWriter();
			writer.append(fileHandler.getFileContentsAsString(file));
			writer.flush();
		}
		catch (FileNotFoundException e) {
			response.setStatus(404);
		}
	}

	private String getFilenameFromURI(String requestURI) {
		return requestURI.split(propertiesProvider.getProperty(Property.javascriptUrl))[1];
	}
}