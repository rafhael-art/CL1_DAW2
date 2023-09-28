/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cibertec.edu.CL1.services;

import com.cibertec.edu.CL1.interfaces.ICarrito;
import com.cibertec.edu.CL1.interfaces.IProducto;
import com.cibertec.edu.CL1.interfacesService.ICarritoService;
import com.cibertec.edu.CL1.interfacesService.IProductoService;
import com.cibertec.edu.CL1.modelo.Carrito;
import com.cibertec.edu.CL1.modelo.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafhael
 */
@Service
public class CarritoService implements ICarritoService{

    @Autowired
    private ICarrito data;
    
    @Override
    public List<Carrito> Listar() {
         return (List<Carrito>) data.findAll();
    }

    @Override
    public Optional<Carrito> Buscar(Long Id) {
        return data.findById(Id);
    }

    @Override
    public int Grabar(Carrito objC) {
        int rpta = 0;
        Carrito Obj = data.save(objC);
        if(!Obj.equals(null)) rpta = 1;
        return rpta;
    }

    @Override
    public void Suprimir(Long Id) {
        data.deleteById(Id);
    }

     
    
}
