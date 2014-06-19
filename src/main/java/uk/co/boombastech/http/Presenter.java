package uk.co.boombastech.http;

import uk.co.boombastech.templating.Template;

import java.io.IOException;

public interface Presenter {
	Template execute(Request request, Response response) throws IOException;
}