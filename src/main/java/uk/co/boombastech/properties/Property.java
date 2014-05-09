package uk.co.boombastech.properties;

public enum Property {
	javascriptPath("STATICCONTENT.PATH.JAVASCRIPT"),
	javascriptUrl("URL.PATH.JAVASCRIPT");

	private final String propertyString;

	Property(String propertyString) {
		this.propertyString = propertyString;
	}

	public String getPropertyString() {
		return propertyString;
	}
}