/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cibertec.edu.CL1.services;

import com.cibertec.edu.CL1.interfaces.IProducto;
import com.cibertec.edu.CL1.interfacesService.IProductoService;
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
public class ProductoService implements IProductoService {

    @Autowired
    private IProducto data;

    @Override
    public List<Producto> Listar() {
        return (List<Producto>) data.findAll();
    }

    @Override
    public Optional<Producto> Buscar(Long Id) {
        return data.findById(Id);
    }

}
