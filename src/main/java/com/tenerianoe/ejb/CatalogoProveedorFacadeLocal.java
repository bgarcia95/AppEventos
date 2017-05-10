/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenerianoe.ejb;

import com.tenerianoe.model.CatalogoProveedor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author said
 */
@Local
public interface CatalogoProveedorFacadeLocal {

    void create(CatalogoProveedor catalogoProveedor);

    void edit(CatalogoProveedor catalogoProveedor);

    void remove(CatalogoProveedor catalogoProveedor);

    CatalogoProveedor find(Object id);

    List<CatalogoProveedor> findAll();

    List<CatalogoProveedor> findRange(int[] range);

    int count();
    
}
