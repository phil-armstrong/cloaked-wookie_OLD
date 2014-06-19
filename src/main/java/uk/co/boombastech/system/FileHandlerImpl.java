package uk.co.boombastech.system;

import uk.co.boombastech.utils.guava.GuavaFilesWrapper;

import javax.inject.Inject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.lang.String.format;

public class FileHandlerImpl implements FileHandler {

	private final GuavaFilesWrapper guavaFilesWrapper;

	@Inject
	public FileHandlerImpl(GuavaFilesWrapper guavaFilesWrapper) {
		this.guavaFilesWrapper = guavaFilesWrapper;
	}

	@Override
	public File getFile(String directory, String filename) throws FileNotFoundException {
		File file = new File(directory, filename);
		if (!file.exists()) {
			throw new FileNotFoundException(format("The file '%s' cannot be found", filename));
		}
		return file;
	}

	@Override
	public String getFileContentsAsString(File file) throws IOException {
		return guavaFilesWrapper.toString(file);
	}
}