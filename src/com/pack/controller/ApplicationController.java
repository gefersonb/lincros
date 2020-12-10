package com.pack.controller;

import java.util.Date;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.pack.login.entities.Usuario;

import com.pack.util.AppUtils;
import com.pack.util.Constants;
import com.pack.util.ResourceUtils;
import com.pack.util.WebConstants;

@Model
public class ApplicationController extends AbstractController<Usuario> {

	private static final long serialVersionUID = 1L;


	@Override
	public Usuario createModel() {
		return null;
	}

	public String logoff() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		session.invalidate();
		String loginPage = super.getRequest().getServletContext().getInitParameter(WebConstants.LOGIN_PAGE);
		return super.redirect(loginPage);
	}

	public Date getSystemDate() {
		return new Date();
	}

	public String getConnectedUserName() {
		return super.getConnectedUser() != null && super.getConnectedUser().getNome() != null
				&& !super.getConnectedUser().getNome().isEmpty() ? super.getConnectedUser().getNome().split(" ")[0]
						: null;
	}

	public boolean isLogged() {
		return this.getConnectedUser() != null;
	}

	public boolean isSecuredRequest() {
		return super.getCurrentFacesContext().getViewRoot().getViewId().contains(WebConstants.SECURED);
	}

	public boolean isAdministrador() {
		return true;
	}

	public boolean isGestor() {
		return true;
	}

	public boolean isConsulta() {
		return true;
	}


	public String getLogoTitle() {
		return "Avaliação técnica";
	}

}