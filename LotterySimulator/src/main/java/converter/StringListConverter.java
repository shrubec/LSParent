package converter;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="StringListConverter")
public class StringListConverter implements Converter {

    public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

    	System.out.println("STRING: " + value);
    	
    	if (value == null || (value != null && value.isEmpty())) return null;
    	String trimmedValue = value.trim();

        try {
            return Integer.parseInt(trimmedValue);
        }
        catch (NumberFormatException ex) {}

        postMessage(arg0, arg1);
        return null;

    }

    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
    	
    	System.out.println("OBJEKT: " + arg2);
    	
        if (arg2 == null) {
            return "";
        }
        try {
            Integer number = (Integer) arg2;
            return number.toString();
        } catch (Exception ex) {
        	Logger.getLogger(getClass().getName()).log(Level.FINEST, "exception caught", ex);
            return "";
        }
    }

    private void postMessage(FacesContext fc, UIComponent comp) {
        FacesContext.getCurrentInstance().addMessage(comp.getClientId(fc),
                new FacesMessage(FacesMessage.SEVERITY_ERROR,"Uneseni broj nije ispravan!","Uneseni broj nije ispravan!"));
    }
    
}