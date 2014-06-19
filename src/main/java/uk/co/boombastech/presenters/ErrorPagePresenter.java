package uk.co.boombastech.presenters;

import uk.co.boombastech.http.Presenter;
import uk.co.boombastech.http.Request;
import uk.co.boombastech.http.Response;
import uk.co.boombastech.templating.FreemarkerTemplate;
import uk.co.boombastech.templating.Template;
import uk.co.boombastech.templating.TemplateName;

import java.io.IOException;

public class ErrorPagePresenter implements Presenter {
    @Override
    public Template execute(Request request, Response response) throws IOException {
		return new FreemarkerTemplate(TemplateName.error);
    }
}
