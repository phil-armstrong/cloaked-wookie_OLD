package uk.co.boombastech.presenters;

import uk.co.boombastech.http.Presenter;
import uk.co.boombastech.templating.FreemarkerTemplate;
import uk.co.boombastech.templating.Template;
import uk.co.boombastech.templating.TemplateKey;
import uk.co.boombastech.templating.TemplateName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PrototypePresenter implements Presenter {

	@Override
	public Template execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		FreemarkerTemplate freemarkerTemplate = new FreemarkerTemplate(TemplateName.cheese);
		freemarkerTemplate.withProperty(TemplateKey.cheese, "bree");
		return freemarkerTemplate;
	}
}