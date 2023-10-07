/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cibertec.edu.CL1.controllers;

import com.cibertec.edu.CL1.dto.CarritoProducto;
import com.cibertec.edu.CL1.interfacesService.ICarritoService;
import com.cibertec.edu.CL1.interfacesService.ICompraService;
import com.cibertec.edu.CL1.interfacesService.IProductoService;
import com.cibertec.edu.CL1.modelo.Carrito;
import com.cibertec.edu.CL1.modelo.Compra;
import com.cibertec.edu.CL1.modelo.Producto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @Autowired
    private ICompraService servicioCompra;

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
                existe = true;
            }
        }

        if (lista.size() == 0 || existe == false) {
            System.out.println("com.cibertec.edu.CL1.controllers.ProductoController.Carrito()");
            Carrito carrito = new Carrito();
            carrito.setProductoId(productoSelecionado.getId());
            carrito.setPrecioTotal(productoSelecionado.getPrecio());
            carrito.setCantidad(1);
            serviciocarrito.Grabar(carrito);
        }
        return "redirect:/listar";
    }

    @GetMapping("/ver-carrito")
    public String Vercarrito(Model m) {
        var lista = serviciocarrito.Listar();

        List<CarritoProducto> current = new ArrayList<CarritoProducto>();
        Double subTotal = 0.0;
        for (var item : lista) {
            var producto = servicio.Buscar(item.getProductoId()).get();
            var newProducto = new CarritoProducto();
            newProducto.setCarritoId(item.getId());
            newProducto.setProductoId(producto.getId());
            newProducto.setCantidad(item.getCantidad());
            newProducto.setPrecioTotal(item.getPrecioTotal());
            newProducto.setDescripcion(producto.getDescripcion());
            newProducto.setUrlImagen(producto.getUrlImagen());
            newProducto.setCategoria(producto.getCategoria());
            newProducto.setPrecio(producto.getPrecio());
            subTotal += item.getPrecioTotal();
            current.add(newProducto);
        }
        m.addAttribute("carrito", current);
        
        var lstcompra = servicioCompra.findAll();
        var compra = new Compra();
        if(lstcompra.size() != 0)
            compra = lstcompra.get(0);            
        compra.setSubTotal(subTotal);
        compra.setTotalPagar(subTotal - (compra.getPrecioEnvio()+compra.getDescuento()));
        servicioCompra.Grabar(compra);
        m.addAttribute("compra", compra);
        return "carrito";
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
                existe = true;
            }
        }

        if (lista.size() == 0 || existe == false) {
            System.out.println("com.cibertec.edu.CL1.controllers.ProductoController.Carrito()");
            Carrito carrito = new Carrito();
            carrito.setProductoId(productoSelecionado.getId());
            carrito.setPrecioTotal(productoSelecionado.getPrecio());
            carrito.setCantidad(1);
            serviciocarrito.Grabar(carrito);
        }
        return "redirect:/ver-carrito";
    }

    @GetMapping("/eliminar/{id}")
    public String EliminarItem(@PathVariable Long id) {
        serviciocarrito.Suprimir(id);
        return "redirect:/ver-carrito";
    }
    
    @PostMapping("/ActualizarCompra")
    public String ActualizarCompra(@Validated Compra compra){
        System.out.println(compra.toString());
        var current = servicioCompra.Buscar(compra.getId()).get();
        current.setDescuento(compra.getDescuento());
        current.setPrecioEnvio(compra.getPrecioEnvio());
        servicioCompra.Grabar(compra);
        return "redirect:/ver-carrito";
    }
    
    @GetMapping("/CambiarCantiad/{id}/{cantidad}")
    public String CambiarCantidad(@PathVariable Long id , @PathVariable int cantidad){
        var carrito = serviciocarrito.Buscar(id).get();
        Producto productoSelecionado = servicio.Buscar(carrito.getProductoId()).get();
        carrito.setCantidad(carrito.getCantidad() + (cantidad));
        carrito.setPrecioTotal(carrito.getCantidad() * productoSelecionado.getPrecio());
        serviciocarrito.Grabar(carrito);
        return "redirect:/ver-carrito";
    }
}
