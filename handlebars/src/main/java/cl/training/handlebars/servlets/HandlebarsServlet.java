package cl.training.handlebars.servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
	private TutorialTemplateRenderer renderer = TutorialTemplateRenderer.getRenderer();
    public HandlebarsServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String template = request.getParameter("template");
		template = (template != null) ? template : "";
		switch (template) {
			case ("hello"):
				renderHelloTemplate(response);
				break;
			default:
				PrintWriter writer = response.getWriter();
				writer.println("TEMPLATE NOT FOUND");
				break;
		}
		
	}
	
	private void renderHelloTemplate(HttpServletResponse response) {
		ExampleView view = new ExampleView();
		view.setTitle("hola mundo");
		view.setParagraph("ejemplo");
		view.setType("example");
		renderer.render(getServletContext(), "hello", view, response);
	}
	
}
