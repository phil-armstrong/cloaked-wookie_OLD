package uk.co.boombastech.wiring;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import uk.co.boombastech.properties.PropertiesModule;
import uk.co.boombastech.staticcontent.HtmlServlet;
import uk.co.boombastech.staticcontent.JavascriptServlet;
import uk.co.boombastech.system.SystemModule;

public class WiringDefinition extends GuiceServletContextListener {
	private final Injector injector;

	public WiringDefinition() {
		ServletModule servletModule = new ServletModule() {
			@Override
			protected void configureServlets() {
				install(new PropertiesModule());
				install(new SystemModule());
				serve("/*").with(HtmlServlet.class);
				serve("/static/javascript/*").with(JavascriptServlet.class);

			}
		};

		this.injector = Guice.createInjector(servletModule);
	}

	@Override
	public Injector getInjector() {
		return this.injector;
	}
}