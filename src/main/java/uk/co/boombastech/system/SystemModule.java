package uk.co.boombastech.system;

import com.google.inject.AbstractModule;
import uk.co.boombastech.properties.PropertiesProvider;
import uk.co.boombastech.properties.PropertiesProviderImpl;

public class SystemModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(FileHandler.class).to(FileHandlerImpl.class);
	}
}