package edu.utn.Modelos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Especialidad {
    private int id;
    public String nombre; // NOMBRE DESCRIPTIVO DE LA ESPECIALIDAD

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMitigacion() {
        return mitigacion;
    }

    public void setMitigacion(String mitigacion) {
        this.mitigacion = mitigacion;
    }

    public String mitigacion; // PROBLEMA QUE PUEDE SOLUCIONAR ESTA ESPECIALIDAD
    // Constructor
    public Especialidad(String nombre, String mitigacion) {
        this.nombre = nombre;
        this.mitigacion = mitigacion;
    }
}
