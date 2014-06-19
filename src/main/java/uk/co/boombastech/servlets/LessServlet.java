package uk.co.boombastech.servlets;

import org.lesscss.LessCompiler;
import org.lesscss.LessException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@Singleton
public class LessServlet extends HttpServlet {

	private final LessCompiler lessCompiler;
	private final File file;
	private String compile;


	@Inject
	public LessServlet(LessCompiler lessCompiler) {
		this.lessCompiler = lessCompiler;
		file = new File("web/less/boombastech.less");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			compile = lessCompiler.compile(file);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LessException e) {
			e.printStackTrace();
		}
		response.setContentType("text/css");
		PrintWriter writer = response.getWriter();
		writer.append(compile);
		writer.flush();
		writer.close();
	}
}