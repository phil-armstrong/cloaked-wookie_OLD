package uk.co.boombastech.templating;

import javax.servlet.http.HttpServletResponse;

public interface TemplateManager {
	void doTemplate(Template template, HttpServletResponse response);
}