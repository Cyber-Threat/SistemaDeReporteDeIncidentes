package edu.utn.Modelos;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Problema {
    public int id; // IDENTIFICACION UNIVOCA PARA GUARDAR EN LA BASE DE DATOS
    public String nombre; // NOMBRE DESCRIPTIVO DEL PROBLEMA
    public String especialidad; // ESPECIALIDAD QUE SOLUCIONA ESTE PROBLEMA
    // Constructor
    public Problema(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }
}
