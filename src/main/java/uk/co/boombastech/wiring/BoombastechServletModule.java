package uk.co.boombastech.wiring;

import com.google.inject.servlet.ServletModule;
import uk.co.boombastech.http.GuicePathManager;
import uk.co.boombastech.http.PathManager;
import uk.co.boombastech.properties.PropertiesModule;
import uk.co.boombastech.servlets.JavascriptServlet;
import uk.co.boombastech.servlets.LessServlet;
import uk.co.boombastech.servlets.PresenterServlet;
import uk.co.boombastech.system.SystemModule;
import uk.co.boombastech.templating.FreemarkerTemplateManager;
import uk.co.boombastech.templating.TemplateManager;
import org.apache.bval.guice.ValidationModule;

public class BoombastechServletModule extends ServletModule {
	@Override
	protected void configureServlets() {
		bind(PathManager.class).to(GuicePathManager.class);
		bind(TemplateManager.class).to(FreemarkerTemplateManager.class);
		install(new PropertiesModule());
		install(new SystemModule());
		install(new ValidationModule());
		serve("/static/css/main.css").with(LessServlet.class);
		serve("/static/javascript/*").with(JavascriptServlet.class);
		serve("/*").with(PresenterServlet.class);
	}
}