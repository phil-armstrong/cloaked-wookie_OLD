package uk.co.boombastech.wiring;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import uk.co.boombastech.properties.PropertiesModule;
import uk.co.boombastech.staticcontent.HtmlServlet;
import uk.co.boombastech.staticcontent.JavascriptServlet;
import uk.co.boombastech.staticcontent.LessServlet;
import uk.co.boombastech.system.SystemModule;

import javax.servlet.annotation.WebListener;

@WebListener
public class WiringDefinition extends GuiceServletContextListener {
	private final Injector injector;

	public WiringDefinition() {
		ServletModule servletModule = new ServletModule() {
			@Override
			protected void configureServlets() {
				install(new PropertiesModule());
				install(new SystemModule());
				serve("/static/css/main.css").with(LessServlet.class);
				serve("/static/javascript/*").with(JavascriptServlet.class);
				serve("/*").with(HtmlServlet.class);
			}
		};

		this.injector = Guice.createInjector(servletModule);
	}

	@Override
	public Injector getInjector() {
		return this.injector;
	}
}