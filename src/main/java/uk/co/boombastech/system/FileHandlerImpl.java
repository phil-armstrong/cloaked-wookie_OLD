package uk.co.boombastech.system;

import java.io.File;

public class FileHandlerImpl implements FileHandler {
	@Override
	public File getFile(String directory, String filename) {
		return new File(directory, filename);
	}
}