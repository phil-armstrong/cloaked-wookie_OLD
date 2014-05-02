package uk.co.boombastech.servers;

import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import uk.co.boombastech.environment.Environment;
import uk.co.boombastech.wiring.WiringDefinition;

public class WebServer extends Server {

	public static void main(String[] args) throws Exception {
		System.setProperty("environment", Environment.dev.name());

		Server webServer = new Server(8080);

		ServletContextHandler serviceContextHelper = new ServletContextHandler(webServer, "/");
		serviceContextHelper.setSessionHandler(new SessionHandler());
		WiringDefinition listener = new WiringDefinition();
		serviceContextHelper.addEventListener(listener);
		serviceContextHelper.addFilter(GuiceFilter.class, "/*", null);
		serviceContextHelper.addServlet(DefaultServlet.class, "/");

		webServer.start();
		webServer.join();
	}
}