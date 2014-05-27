package uk.co.boombastech.wiring;

import com.google.inject.servlet.ServletModule;
import uk.co.boombastech.properties.PropertiesModule;
import uk.co.boombastech.servlets.PresenterServlet;
import uk.co.boombastech.staticcontent.JavascriptServlet;
import uk.co.boombastech.staticcontent.LessServlet;
import uk.co.boombastech.system.SystemModule;

public class BoombastechServletModule extends ServletModule {
	@Override
	protected void configureServlets() {
		bind(PathManager.class);
		install(new PropertiesModule());
		install(new SystemModule());
		serve("/static/css/main.css").with(LessServlet.class);
		serve("/static/javascript/*").with(JavascriptServlet.class);
		serve("/*").with(PresenterServlet.class);
	}
}