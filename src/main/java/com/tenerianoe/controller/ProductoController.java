/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenerianoe.controller;


import com.tenerianoe.ejb.CatalogoProductoFacadeLocal;
import com.tenerianoe.model.CatalogoProducto;
import java.io.Serializable;
import java.util.List;
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
public class ProductoController implements Serializable {

    @EJB
    private CatalogoProductoFacadeLocal productoEJB;

    private CatalogoProducto producto;

    private List<CatalogoProducto> productos;

    @PostConstruct
    public void init() {
        producto = new CatalogoProducto();
        productos = productoEJB.findAll();

    }

    public void registrar() {
        try {
            productoEJB.create(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));

        }

    }
    
        public void modificar() {
        try {
            productoEJB.edit(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));

        }

    }

    //Getter y Setter
    public CatalogoProducto getProducto() {
        return producto;
    }

    public void setProducto(CatalogoProducto producto) {
        this.producto = producto;
    }

    public List<CatalogoProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<CatalogoProducto> productos) {
        this.productos = productos;
    }

}
