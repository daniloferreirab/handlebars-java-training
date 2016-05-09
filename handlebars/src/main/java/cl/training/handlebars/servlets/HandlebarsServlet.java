package cl.training.handlebars.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.training.handlebars.TutorialTemplateRenderer;
import cl.training.handlebars.views.ContactsView;
import cl.training.handlebars.views.ExampleView;
import cl.training.handlebars.views.PersonView;

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
			case ("person"):
				renderPersonTemplate(response);
				break;
			case ("contacts"):
				renderContactsTemplate(response);
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
	private void renderPersonTemplate(HttpServletResponse response){
		PersonView person = new PersonView();
		person.setDireccion("Varas #898, edificio capital");
		person.setEdad("28");
		person.setGenero("Masculiuno");
		person.setNombre("Oxlade Xamberlain");
		renderer.render(getServletContext(), "person", person, response);
	}
	public void renderContactsTemplate (HttpServletResponse response){
		ContactsView contacts = new ContactsView();
		ArrayList<PersonView> contactList = new ArrayList<PersonView>();
		contacts.setTitulo("Contactos");
		PersonView person = new PersonView();
				person.setDireccion("Varas #898, edificio capital");
				person.setEdad("28");
				person.setGenero("Masculiuno");
				person.setNombre("Oxlade Xamberlain");
		PersonView person2= new PersonView();
				person2.setDireccion("Varas #898, edificio capital");
				person2.setEdad("26");
				person2.setGenero("Femenino");
				person2.setNombre("Demi Rose");
		contactList.add(person);
		contactList.add(person2);
		contacts.setContactList(contactList);
		
		renderer.render(getServletContext(), "contacts", contacts, response);
	}
	
}
