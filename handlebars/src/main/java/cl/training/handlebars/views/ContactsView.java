/**
 * 
 */
package cl.training.handlebars.views;

import java.util.ArrayList;

/**
 * @author dferreib
 *
 */
public class ContactsView {
	private ArrayList<PersonView> contactList = new ArrayList<PersonView>();
	private String titulo;
	
	/**
	 * constructor
	 */
	public ContactsView(){
		
	}

	/**
	 * @return the contactList
	 */
	public ArrayList<PersonView> getContactList() {
		return contactList;
	}

	/**
	 * @param contactList the contactList to set
	 */
	public void setContactList(ArrayList<PersonView> contactList) {
		this.contactList = contactList;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	

}
