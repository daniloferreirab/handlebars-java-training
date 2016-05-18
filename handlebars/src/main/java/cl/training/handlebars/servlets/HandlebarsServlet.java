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
import cl.training.handlebars.views.Agenda;
import cl.training.handlebars.views.GroupView;
import cl.training.handlebars.views.ExampleView;
import cl.training.handlebars.views.ContactView;

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
			case ("contact"):
				renderContactTemplate(response);
				break;
			case ("group"):
				renderGroupTemplate(response);
				break;
			case ("main"):
				renderMainTemplate(response);
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
	private void renderContactTemplate(HttpServletResponse response){
		ContactView person = new ContactView();
		person.setDireccion("Varas #898, edificio capital");
		person.setEdad("28");
		person.setGenero("Masculiuno");
		person.setNombre("Oxlade Xamberlain");
		renderer.render(getServletContext(), "contact", person, response);
	}
	public void renderGroupTemplate (HttpServletResponse response){
		GroupView contacts = new GroupView();
		ArrayList<ContactView> contactList = new ArrayList<ContactView>();
		contacts.setTitulo("Contactos");
		ContactView person = new ContactView();
				person.setDireccion("Varas #898, edificio capital");
				person.setEdad("28");
				person.setGenero("Masculiuno");
				person.setNombre("Oxlade Xamberlain");
		ContactView person2= new ContactView();
				person2.setDireccion("Varas #898, edificio capital");
				person2.setEdad("26");
				person2.setGenero("Femenino");
				person2.setNombre("Demi Rose");
		contactList.add(person);
		contactList.add(person2);
		contacts.setContactList(contactList);
		
		renderer.render(getServletContext(), "group", contacts, response);
	}
	
	public void renderMainTemplate (HttpServletResponse response){
		GroupView contacts = new GroupView();
		ArrayList<ContactView> contactList = new ArrayList<ContactView>();
		contacts.setTitulo("Contactos");
		ContactView person = new ContactView();
				person.setDireccion("Varas #898, edificio capital");
				person.setEdad("28");
				person.setGenero("Masculiuno");
				person.setNombre("Oxlade Xamberlain");
		ContactView person2= new ContactView();
				person2.setDireccion("Varas #898, edificio capital");
				person2.setEdad("26");
				person2.setGenero("Femenino");
				person2.setNombre("Demi Rose");
		ContactView person3= new ContactView();
			person3.setDireccion("Varas #898, edificio capital");
			person3.setEdad("26");
			person3.setGenero("Femenino");
			person3.setNombre("Demi Rose");
		contactList.add(person);
		contactList.add(person2);
		contactList.add(person3);
		contacts.setContactList(contactList);
		
		ContactView person0 = new ContactView();
		person0.setDireccion("Varas #898, edificio capital");
		person0.setEdad("28");
		person0.setGenero("Masculiuno");
		person0.setNombre("Oxlade Xamberlain");
		Agenda agenda =  new Agenda();
		agenda.addContacto(person0);
		agenda.addGrupo(contacts);
		renderer.render(getServletContext(), "main", agenda, response);
	}
	
}
