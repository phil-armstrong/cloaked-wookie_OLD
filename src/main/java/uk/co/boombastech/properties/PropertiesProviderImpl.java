package uk.co.boombastech.properties;

import uk.co.boombastech.environment.Environment;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.Properties;

@Singleton
public class PropertiesProviderImpl implements PropertiesProvider {

	private final Properties properties;

	@Inject
	public PropertiesProviderImpl() {
		properties = new Properties();
		Environment environment = Environment.valueOf(System.getProperty("environment"));

		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream(environment.getPropertiesFile()));
		} catch (IOException exception) {
			System.out.println("Couldn't load properties file: " + environment + ".properties");
			System.exit(1);
		}
	}

	@Override
	public String getProperty(Property property) {
		return properties.getProperty(property.getPropertyString());
	}
}