package uk.co.boombastech.templating;

public enum TemplateName {
	homepage("path", "filename"),
	error("error"),
	cheese("cheese");

	private final String path;
	private final String filename;

	TemplateName(String path, String filename) {
		this.path = path + "/";
		this.filename = filename;
	}


	TemplateName(String filename) {
		this.path = "";
		this.filename = filename;
	}

	public String getNameWithExtension(String extension) {
		return path + filename + extension;
	}
}