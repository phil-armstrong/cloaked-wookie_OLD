package uk.co.boombastech.database;

import com.google.inject.AbstractModule;

public class DatabaseModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Database.class);
	}
}