package facade;

import hr.shrubec.simulacija.bean.SimulacijaBean;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import entity.Kontakt;

@ManagedBean
@ViewScoped
public class KontaktFacade {

	
	@EJB SimulacijaBean simulacijaBean;
	
	
	private String name;
	private String email;
	private String message;

	public void saveMessage() {
		System.out.println(name + ", " + email + ", " + message);
		
		Kontakt kontakt=new Kontakt();
		kontakt.setDate(new Date());
		kontakt.setEmail(email);
		kontakt.setName(name);
		kontakt.setMessage(message);
		
		simulacijaBean.saveKontakt(kontakt);
		
		FacesMessage msg = new FacesMessage("Your message has been sent!", "Your message has been sent!");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        
        name=null;
        email=null;
        message=null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
