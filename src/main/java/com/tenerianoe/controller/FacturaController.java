/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenerianoe.controller;

import com.tenerianoe.ejb.CatalogoProductoFacadeLocal;
import com.tenerianoe.ejb.CatalogoProveedorFacadeLocal;
import com.tenerianoe.ejb.FacturaFacadeLocal;
import com.tenerianoe.model.CatalogoProducto;
import com.tenerianoe.model.CatalogoProveedor;
import com.tenerianoe.model.DetalleFactura;
import com.tenerianoe.model.Factura;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author said
 */
@Named
@ViewScoped
public class FacturaController {

      //Objetos 
      private CatalogoProveedor proveedorSeleccionado;
      private CatalogoProducto productoSeleccionado;
      private Factura factura;
      private DetalleFactura detalleFactura;

      //EJBS
      @EJB
      private FacturaFacadeLocal facturaEJB;
      @EJB
      private CatalogoProveedorFacadeLocal proveedorEJB;
      @EJB
      private CatalogoProductoFacadeLocal productoEJB;

      //Listas
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

}
