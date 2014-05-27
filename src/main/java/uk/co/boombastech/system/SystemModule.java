package uk.co.boombastech.system;

import com.google.inject.AbstractModule;

public class SystemModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(FileHandler.class).to(FileHandlerImpl.class);
	}
}