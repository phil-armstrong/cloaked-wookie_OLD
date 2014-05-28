package uk.co.boombastech.templating;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class FreemarkerTemplate implements Template {
	private final TemplateName templateName;
	private final Map<String, String> properties;

	public FreemarkerTemplate(TemplateName templateName) {
		this.templateName = templateName;
		properties = newHashMap();
	}


	@Override
	public void withProperty(TemplateKey key, String value) {
		properties.put(key.name(), value);
	}

	@Override
	public String getTemplateName() {
		return templateName.getNameWithExtension(".ftl");
	}

	@Override
	public Map<String, String> getTemplateProperties() {
		return properties;
	}
}