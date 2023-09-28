/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cibertec.edu.CL1.interfacesService;

import com.cibertec.edu.CL1.modelo.Producto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rafhael
 */
public interface IProductoService {
     public List<Producto> Listar();
    public Optional<Producto> Buscar(Long Id);
}
