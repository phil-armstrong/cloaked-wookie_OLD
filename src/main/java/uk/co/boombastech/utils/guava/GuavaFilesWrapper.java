package uk.co.boombastech.utils.guava;

import com.google.common.io.Files;

import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@Singleton
public class GuavaFilesWrapper {

	public String toString(File file) throws IOException {
		return Files.toString(file, Charset.defaultCharset());
	}

	public String toString(File file, Charset charset) throws IOException {
		return Files.toString(file, charset);
	}
}