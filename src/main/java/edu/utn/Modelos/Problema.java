package edu.utn.Modelos;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Problema {
    public Integer id; // IDENTIFICACION UNIVOCA PARA GUARDAR EN LA BASE DE DATOS
    public Integer idIncidenteVinculado;
    public Integer idServicioVinculado;
    public String nombre; // NOMBRE DESCRIPTIVO DEL PROBLEMA
    public String especialidad; // ESPECIALIDAD QUE SOLUCIONA ESTE PROBLEMA
    public LocalDate tiempoEstimadoDeResolucion;
    public String consideracionesDeCierre;
    private LocalDate fechaResolucion;
    // Constructor
    public Problema(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }
}
