/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cibertec.edu.CL1.interfacesService;

import com.cibertec.edu.CL1.modelo.Compra;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rafhael
 */
public interface ICompraService {

    public List<Compra> findAll();
    
    public Optional<Compra> Buscar(Long Id);

    public int Grabar(Compra objC);
}
