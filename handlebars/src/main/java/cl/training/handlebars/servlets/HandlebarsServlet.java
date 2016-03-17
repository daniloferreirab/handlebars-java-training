package cl.training.handlebars.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.training.handlebars.TutorialTemplateRenderer;
import cl.training.handlebars.views.ExampleView;

@WebServlet("/training")
public class HandlebarsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HandlebarsServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ExampleView view = new ExampleView();
		System.out.println(request.getParameter("template"));
		view.setTitle("hola mundo");
		view.setParagraph("ejemplo");
		TutorialTemplateRenderer renderer = TutorialTemplateRenderer.getRenderer();
		renderer.render(getServletContext(), "hello", view, response);
	}

}
