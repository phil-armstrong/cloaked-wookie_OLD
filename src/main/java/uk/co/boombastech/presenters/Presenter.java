package uk.co.boombastech.presenters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Presenter {
	void execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}