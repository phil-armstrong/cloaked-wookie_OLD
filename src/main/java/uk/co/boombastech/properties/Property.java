package uk.co.boombastech.properties;

public enum Property {
	javascriptPath("STATICCONTENT.PATH.JAVASCRIPT"),
	javascriptUrl("URL.PATH.JAVASCRIPT"),
	environment("ENVIRONMENT"),
	databaseDriver("dataSource.driver"),
	databaseUsername("dataSource.username"),
	databasePassword("dataSource.password"),
	databaseUrl("dataSource.url"),
	databaseHeartbeatSql("dataSource.heartbeatSql");

	private final String propertyString;

	Property(String propertyString) {
		this.propertyString = propertyString;
	}

	public String getPropertyString() {
		return propertyString;
	}
}