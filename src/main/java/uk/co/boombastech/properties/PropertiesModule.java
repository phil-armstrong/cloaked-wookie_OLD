package uk.co.boombastech.properties;

import com.google.inject.AbstractModule;
import uk.co.boombastech.properties.PropertiesProvider;
import uk.co.boombastech.properties.PropertiesProviderImpl;

public class PropertiesModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(PropertiesProvider.class).to(PropertiesProviderImpl.class);
	}
}