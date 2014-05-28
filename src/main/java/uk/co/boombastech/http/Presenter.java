package uk.co.boombastech.http;

import uk.co.boombastech.templating.Template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Presenter {
	Template execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}