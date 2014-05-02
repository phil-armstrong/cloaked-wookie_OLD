package uk.co.boombastech.staticcontent;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import uk.co.boombastech.properties.PropertiesProvider;

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
public class JavascriptServlet extends HttpServlet {

	private final PropertiesProvider propertiesProvider;

	@Inject
	public JavascriptServlet(PropertiesProvider propertiesProvider) {
		this.propertiesProvider = propertiesProvider;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String fileName = stripUrl(requestURI);
		File file = new File(propertiesProvider.getProperty("javascriptPath"), fileName);

		if (!file.exists()) {
			response.setStatus(404);
			return;
		}

		PrintWriter writer = response.getWriter();
		writer.append(Files.toString(file, Charsets.UTF_8));
		writer.flush();
	}

	private String stripUrl(String requestURI) {
		return requestURI.split("static/javascript")[1];
	}
}