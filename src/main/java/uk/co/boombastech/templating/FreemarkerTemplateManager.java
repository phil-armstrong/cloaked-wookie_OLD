package uk.co.boombastech.templating;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Singleton
public class FreemarkerTemplateManager implements TemplateManager {

	private final Configuration configuration;

	@Inject
	public FreemarkerTemplateManager() {
		configuration = new Configuration();
		try {
			configuration.setDirectoryForTemplateLoading(new File("web/template/freemarker"));
			configuration.setObjectWrapper(new DefaultObjectWrapper());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Template getTemplate(String name) {
		try {
			return configuration.getTemplate(name + ".ftl");
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public void doTemplate(uk.co.boombastech.templating.Template template, HttpServletResponse response) {
		try {
			Template freemarkerTemplate = configuration.getTemplate(template.getTemplateName());
			freemarkerTemplate.process(template.getTemplateProperties(), response.getWriter());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}

	}
}