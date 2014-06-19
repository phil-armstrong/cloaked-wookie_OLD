package uk.co.boombastech.servlets;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.boombastech.properties.PropertiesProvider;
import uk.co.boombastech.properties.Property;
import uk.co.boombastech.system.FileHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JavascriptServletTest {

	@Mock
	private PropertiesProvider propertiesProvider;
	@Mock
	private FileHandler fileHandler;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpServletRequest request;
	@Mock
	private File file;
	@Mock
	private PrintWriter printWriter;

	private JavascriptServlet javascriptServlet;

	@Before
	public void setUp() throws Exception {
		javascriptServlet = new JavascriptServlet(propertiesProvider, fileHandler);

		when(propertiesProvider.getProperty(Property.javascriptPath)).thenReturn("/javascript");
		when(propertiesProvider.getProperty(Property.javascriptUrl)).thenReturn("static/javascript/");

		when(fileHandler.getFileContentsAsString(file)).thenReturn("filecontents bla bla bla");

		when(request.getRequestURI()).thenReturn("static/javascript/testFile.js");
		when(response.getWriter()).thenReturn(printWriter);
	}

	@Test
	public void shouldReturn200ResponseForFile() throws Exception {
		when(fileHandler.getFile("/javascript", "testFile.js")).thenReturn(file);
		when(file.exists()).thenReturn(true);

		javascriptServlet.doGet(request, response);

		verify(response, never()).setStatus(404);
		verify(printWriter).append("filecontents bla bla bla");
		verify(printWriter).flush();
	}

	@Test
	public void shouldReturn400ResponseForFileNotFound() throws Exception {
		when(fileHandler.getFile("/javascript", "testFile.js")).thenThrow(new FileNotFoundException());

		javascriptServlet.doGet(request, response);

		verify(response).setStatus(404);
		verify(printWriter, never()).append(anyString());
		verify(printWriter, never()).flush();
	}
}