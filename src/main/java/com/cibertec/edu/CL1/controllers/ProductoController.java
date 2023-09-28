/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cibertec.edu.CL1.controllers;

import com.cibertec.edu.CL1.dto.CarritoProducto;
import com.cibertec.edu.CL1.interfacesService.ICarritoService;
import com.cibertec.edu.CL1.interfacesService.IProductoService;
import com.cibertec.edu.CL1.modelo.Carrito;
import com.cibertec.edu.CL1.modelo.Producto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author rafhael
 */
@Controller
@RequestMapping
public class ProductoController {

    @Autowired
    private IProductoService servicio;

    @Autowired
    private ICarritoService serviciocarrito;

    @GetMapping("/listar")
    public String Listado(Model m) {
        List<Producto> lista = servicio.Listar();
        m.addAttribute("productos", lista);
        return "listado";
    }
    
    
    @GetMapping("/agregar/{id}")
    public String Listado(Model m, @PathVariable Long id) {
       Producto productoSelecionado = servicio.Buscar(id).get();
        List<Carrito> lista = serviciocarrito.Listar();
        
        Boolean existe = false;      

        for (Carrito objeto : lista) {
            if (objeto.getProductoId() == productoSelecionado.getId()) {
                objeto.setCantidad(objeto.getCantidad() + 1);
                objeto.setPrecioTotal(objeto.getCantidad() * productoSelecionado.getPrecio());
                serviciocarrito.Grabar(objeto);
                existe=true;
            }            
        }
        
        
        
        if (lista.size()==0 || existe == false) {
            System.out.println("com.cibertec.edu.CL1.controllers.ProductoController.Carrito()");
            Carrito carrito = new Carrito();
                carrito.setProductoId(productoSelecionado.getId());
                carrito.setPrecioTotal(productoSelecionado.getPrecio());
                carrito.setCantidad(1);
                serviciocarrito.Grabar(carrito);
        }
        return "redirect:/listar";
    }
    
    
    

    @GetMapping("/carrito/{id}")
    public String Carrito(Model m, @PathVariable Long id) {

        Producto productoSelecionado = servicio.Buscar(id).get();
        List<Carrito> lista = serviciocarrito.Listar();
        
        Boolean existe = false;      

        for (Carrito objeto : lista) {
            if (objeto.getProductoId() == productoSelecionado.getId()) {
                objeto.setCantidad(objeto.getCantidad() + 1);
                objeto.setPrecioTotal(objeto.getCantidad() * productoSelecionado.getPrecio());
                serviciocarrito.Grabar(objeto);
                existe=true;
            }            
        }
        
        
        
        if (lista.size()==0 || existe == false) {
            System.out.println("com.cibertec.edu.CL1.controllers.ProductoController.Carrito()");
            Carrito carrito = new Carrito();
                carrito.setProductoId(productoSelecionado.getId());
                carrito.setPrecioTotal(productoSelecionado.getPrecio());
                carrito.setCantidad(1);
                serviciocarrito.Grabar(carrito);
        }
        
        
        lista = serviciocarrito.Listar();
        
        List<CarritoProducto> current = new ArrayList<CarritoProducto>();
        
       
        
        
        m.addAttribute("carrito", lista);
        return "carrito";
    }
}
