package uk.co.boombastech.environment;

public enum Environment {
	dev;

	public String getPropertiesFile() {
		return this.name() + ".properties";
	}
}