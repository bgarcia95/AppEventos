package com.tenerianoe.controller;

import com.tenerianoe.ejb.PersonaFacadeLocal;
import com.tenerianoe.ejb.UsuarioFacadeLocal;
import com.tenerianoe.model.Persona;
import com.tenerianoe.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author said
 */
@Named
@ViewScoped

public class UsuarioController implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    @EJB
    private PersonaFacadeLocal personaEJB;

    private Usuario usuario;
    private Persona persona;

    private List<Persona> personas;
    private List<Usuario> usuarios;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        persona = new Persona();
        personas = personaEJB.findAll();
        usuarios = usuarioEJB.findAll();

    }

    public void registrar() {
        try {
            this.usuario.setId_usuario(persona);
            usuarioEJB.create(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));
        }
    }

    public void modificar() {
        try {
            this.usuario.setId_usuario(persona);
            usuarioEJB.edit(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Docente registrado con éxito"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }

    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
