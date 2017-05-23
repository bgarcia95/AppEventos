package com.tenerianoe.controller;

import com.tenerianoe.ejb.UsuarioFacadeLocal;
import com.tenerianoe.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author said
 */
@Named
@ViewScoped
public class LoginController implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    private Usuario usuario;

    @PostConstruct
    public void init() {
        usuario = new Usuario();

    }

    public String iniciarSesion() {
        String redireccion = null;

        try {
            Usuario us;
            us = usuarioEJB.iniciarSesion(usuario);

            if (us != null) {
                redireccion = "/inicioAdm.xhtml?faces-redirect=true";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Inicio de Sesión exitoso"));
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));

            }
        } catch (Exception e) {
        }

        return redireccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
