/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cibertec.edu.CL1.interfacesService;

import com.cibertec.edu.CL1.modelo.Carrito;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rafhael
 */
public interface ICarritoService {

    public List<Carrito> Listar();

    public Optional<Carrito> Buscar(Long Id);

    public int Grabar(Carrito objC);

    public void Suprimir(Long Id);
}
