package uk.co.boombastech.templating;

import java.util.Map;

public interface Template {
	void withProperty(TemplateKey key, String value);
	String getTemplateName();
	Map<String, String> getTemplateProperties();
}