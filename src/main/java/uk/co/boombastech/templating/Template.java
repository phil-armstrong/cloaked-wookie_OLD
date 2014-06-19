package uk.co.boombastech.templating;

import java.util.Map;

public interface Template {
	FreemarkerTemplate withProperty(TemplateKey key, String value);
	String getTemplateName();
	Map<String, String> getTemplateProperties();
}