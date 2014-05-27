package uk.co.boombastech.presenters;

import uk.co.boombastech.http.Presenter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PrototypePresenter implements Presenter {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().write("Cheese");
	}
}