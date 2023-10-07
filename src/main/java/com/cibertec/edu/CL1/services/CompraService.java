/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cibertec.edu.CL1.services;

import com.cibertec.edu.CL1.interfaces.ICompra;
import com.cibertec.edu.CL1.interfacesService.ICompraService;
import com.cibertec.edu.CL1.modelo.Compra;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafhael
 */
@Service
public class CompraService implements ICompraService{
      @Autowired
    private ICompra data;

    @Override
    public Optional<Compra> Buscar(Long Id) {
        return data.findById(Id);
    }

    @Override
    public int Grabar(Compra objC) {
        int rpta = 0;
        Compra Obj = data.save(objC);
        if(!Obj.equals(null)) rpta = 1;
        return rpta;
    }

    @Override
    public List<Compra> findAll() {
        return data.findAll();
    }
}
