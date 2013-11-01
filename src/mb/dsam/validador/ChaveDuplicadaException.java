package mb.dsam.validador;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ChaveDuplicadaException {
	private  FacesContext facesContext = FacesContext.getCurrentInstance();
	
	
	public void ChaveDuplicada(){
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " O valor do Serial está duplicado!", null));
	}
	
	
}
