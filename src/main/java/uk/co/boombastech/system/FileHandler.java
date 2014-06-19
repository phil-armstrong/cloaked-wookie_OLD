package uk.co.boombastech.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileHandler {
	File getFile(String directory, String filename) throws FileNotFoundException;

	String getFileContentsAsString(File file) throws IOException;
}