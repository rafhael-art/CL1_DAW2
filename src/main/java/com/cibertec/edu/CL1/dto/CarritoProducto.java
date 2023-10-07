/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cibertec.edu.CL1.dto;

import lombok.Data;

/**
 *
 * @author rafhael
 */
@Data
public class CarritoProducto {
    private Long CarritoId;
    private Long ProductoId;
    private int Cantidad;
    private double PrecioTotal;
    private String Descripcion;
    private String UrlImagen;
    private String Categoria;
    private double Precio;
}
