/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenerianoe.ejb;

import com.tenerianoe.model.CatalogoProducto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author said
 */
@Local
public interface CatalogoProductoFacadeLocal {

      void create(CatalogoProducto catalogoProducto);

      void edit(CatalogoProducto catalogoProducto);

      void remove(CatalogoProducto catalogoProducto);

      CatalogoProducto find(Object id);

      List<CatalogoProducto> findAll();

      List<CatalogoProducto> findRange(int[] range);

      int count();
      
}
