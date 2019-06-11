package br.com.uniasselvi.carteira.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class FacesUtil {

	public static void addErrorMessage(String msg) {
		addMessage(FacesMessage.SEVERITY_ERROR, msg);
	}

	public static void addWarningMessage(String msg) {
		addMessage(FacesMessage.SEVERITY_WARN, msg);
	}

	public static void addSuccessMessage(String msg) {
		addMessage(FacesMessage.SEVERITY_INFO, msg);
	}

	private static void addMessage(Severity severity, String msg) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setRedirect(true);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, msg, ""));
	}

}
