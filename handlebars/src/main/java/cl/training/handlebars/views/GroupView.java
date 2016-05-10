/**
 * 
 */
package cl.training.handlebars.views;

import java.util.ArrayList;

/**
 * @author dferreib
 * sadda
 */
public class GroupView {
	private ArrayList<ContactView> contactList = new ArrayList<ContactView>();
	private String titulo;
	private String privacidad;
	
	/**
	 * constructor
	 */
	public GroupView(){
		
	}

	/**
	 * @return the contactList
	 */
	public ArrayList<ContactView> getContactList() {
		return contactList;
	}

	/**
	 * @param contactList the contactList to set
	 */
	public void setContactList(ArrayList<ContactView> contactList) {
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

	/**
	 * @return the privacidad
	 */
	public String getPrivacidad() {
		return privacidad;
	}

	/**
	 * @param privacidad the privacidad to set
	 */
	public void setPrivacidad(String privacidad) {
		this.privacidad = privacidad;
	}
	

}
