package uk.co.boombastech.staticcontent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import uk.co.boombastech.properties.PropertiesProvider;
import uk.co.boombastech.properties.Property;
import uk.co.boombastech.system.FileHandler;
import uk.co.boombastech.utils.guava.GuavaFilesWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class JavascriptServletTest {

	@Mock
	private PropertiesProvider propertiesProvider;
	@Mock
	private FileHandler fileHandler;
	@Mock
	private GuavaFilesWrapper guavaWrapper;
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
		initMocks(this);
		javascriptServlet = new JavascriptServlet(propertiesProvider, fileHandler, guavaWrapper);

		when(propertiesProvider.getProperty(Property.javascriptPath)).thenReturn("/javascript");
		when(propertiesProvider.getProperty(Property.javascriptUrl)).thenReturn("static/javascript/");
		when(fileHandler.getFile("/javascript", "testFile.js")).thenReturn(file);
		when(guavaWrapper.toString(file)).thenReturn("filecontents bla bla bla");

		when(request.getRequestURI()).thenReturn("static/javascript/testFile.js");
		when(response.getWriter()).thenReturn(printWriter);
	}

	@Test
	public void shouldReturn200ResponseForFile() throws Exception {
		when(file.exists()).thenReturn(true);

		javascriptServlet.doGet(request, response);

		verify(response, never()).setStatus(404);
		verify(printWriter).append("filecontents bla bla bla");
		verify(printWriter).flush();
	}

	@Test
	public void shouldReturn400ResponseForFileNotFound() throws Exception {
		when(file.exists()).thenReturn(false);

		javascriptServlet.doGet(request, response);

		verify(response).setStatus(404);
		verify(printWriter, never()).append(anyString());
		verify(printWriter, never()).flush();
	}
}