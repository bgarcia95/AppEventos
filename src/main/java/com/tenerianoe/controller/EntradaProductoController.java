/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenerianoe.controller;

import com.tenerianoe.ejb.CatalogoProductoFacadeLocal;
import com.tenerianoe.ejb.CatalogoProveedorFacadeLocal;
import com.tenerianoe.ejb.DetalleFacturaFacadeLocal;
import com.tenerianoe.ejb.FacturaFacadeLocal;
import com.tenerianoe.model.CatalogoProducto;
import com.tenerianoe.model.CatalogoProveedor;
import com.tenerianoe.model.DetalleFactura;
import com.tenerianoe.model.Factura;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author said
 */
@Named
@ViewScoped
public class EntradaProductoController implements Serializable {

    private CatalogoProveedor proveedorSeleccionado;
    private CatalogoProducto productoSeleccionado;
    private Double cantidadProducto;
    private Factura factura;
    private BigDecimal precioUnitario;
    private DetalleFactura detalleFactura;
    private DetalleFacturaFacadeLocal detalleEJB;
    private BigDecimal totalDetalle;

    @EJB
    private FacturaFacadeLocal facturaEJB;
    @EJB
    private CatalogoProveedorFacadeLocal proveedorEJB;
    @EJB
    private CatalogoProductoFacadeLocal productoEJB;

    private List<CatalogoProveedor> proveedores;

    private List<DetalleFactura> listaDetalleFactura;

    @PostConstruct
    public void init() {

        proveedorSeleccionado = new CatalogoProveedor();
        productoSeleccionado = new CatalogoProducto();
        factura = new Factura();
        proveedores = proveedorEJB.findAll();
        listaDetalleFactura = new ArrayList<>();
        detalleFactura = new DetalleFactura();

    }

    public void modificar() {
        try {
            calcularTotalFacturaCompra();
            detalleEJB.edit(detalleFactura);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se modifico"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "No se modifico!"));

        }
    }

    public void guardarDetalle1() {
        detalleEJB.create(detalleFactura);

    }

    public void pedirCantidadProducto(SelectEvent event) {
        productoSeleccionado = ((CatalogoProducto) event.getObject());

    }

    public void onRowSelectProveedor(SelectEvent event) {
        proveedorSeleccionado = ((CatalogoProveedor) event.getObject());

    }

    //Metodo para solicitar cantidad de producto comprado
    public void onRowSelectProducto(SelectEvent event) {
        productoSeleccionado = ((CatalogoProducto) event.getObject());

        //Asigno los valores del producto y proveedor agregado al detalle
        this.listaDetalleFactura.add(detalleFactura = new DetalleFactura(0, null, productoSeleccionado, proveedorSeleccionado, this.cantidadProducto, this.precioUnitario, totalDetalle));
    }

    public void guardarDetalle() {
        try {
            
      
        if (detalleFactura.getCantidad() == 0 || detalleFactura.getPrecioUnitario().floatValue() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "EL valor ingresado es incorrecto"));
        } else {

            this.listaDetalleFactura.add(detalleFactura = new DetalleFactura(0, null, productoSeleccionado, proveedorSeleccionado, detalleFactura.getCantidad(), detalleFactura.getPrecioUnitario(), BigDecimal.valueOf(detalleFactura.getPrecioUnitario().floatValue() * detalleFactura.getCantidad().floatValue())));
            this.detalleFactura = new DetalleFactura();
            this.productoSeleccionado= new CatalogoProducto();
            //Calcula total de la venta
            calcularTotalFacturaCompra();
        }
          } catch (Exception e) {
        }
    }

    public void seleccion(CatalogoProveedor seleccion) {
        proveedorSeleccionado = seleccion;
    }

    public void handleKeyEvent() {
        FacesContext.getCurrentInstance().getAttributes().keySet().add(cantidadProducto);
    }

    public CatalogoProveedor getProveedorSeleccionado() {
        return proveedorSeleccionado;

    }

    public void setProveedorSeleccionado(CatalogoProveedor proveedorSeleccionado) {
        this.proveedorSeleccionado = proveedorSeleccionado;
    }

    public List<CatalogoProveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<CatalogoProveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public List<DetalleFactura> getListaDetalleFactura() {
        return listaDetalleFactura;
    }

    public void setListaDetalleFactura(List<DetalleFactura> listaDetalleFactura) {
        this.listaDetalleFactura = listaDetalleFactura;
    }

    public CatalogoProducto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(CatalogoProducto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public Double getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Double cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void onCellEditPrecio(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        detalleFactura.setPrecioUnitario((BigDecimal) event.getNewValue());

    }

    public void onCellEditCantidad(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        detalleFactura.setCantidad((Double) event.getNewValue());

    }

    public void generarTotal() {

        this.detalleFactura.setTotalCompra(this.detalleFactura.getPrecioUnitario().multiply(new BigDecimal(this.detalleFactura.getCantidad().floatValue())));

    }

    public DetalleFactura getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(DetalleFactura detalleFactura) {
        this.detalleFactura = detalleFactura;
    }

    public void calcularTotalFacturaCompra() {
        BigDecimal totalFacturaCompra = new BigDecimal(0);

        try {
            for (DetalleFactura item : listaDetalleFactura) {
                BigDecimal totalCompraPorProducto = item.getPrecioUnitario().multiply(new BigDecimal(item.getCantidad()));
                item.setTotalCompra(totalCompraPorProducto);
                totalFacturaCompra = totalFacturaCompra.add(totalCompraPorProducto);
            }
            this.factura.setTotalVenta(totalFacturaCompra);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
    public void quitarProductoDetalleFactura(){
    detalleEJB.remove(detalleFactura);
    this.calcularTotalFacturaCompra();
    
    }
    
    //Metodo para limpiar Factura
    public void limpiarFactura(){
    this.proveedorSeleccionado = new CatalogoProveedor();
    this.factura = new Factura();
    this.listaDetalleFactura= new ArrayList<>();
    this.totalDetalle=null;
    this.productoSeleccionado= new CatalogoProducto();
    }
    
    //Metodo para guardar Factura de compra
    
    public void guardarCompra(){
          try {
                for (DetalleFactura item : listaDetalleFactura) {
                      detalleEJB.create(item);
                }
          } catch (Exception e) {
          }
    }
}
