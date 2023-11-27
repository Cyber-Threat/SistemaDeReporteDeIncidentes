package edu.utn.Modelos;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Especialidad {
    @Id
    private int id;
    public String nombre;
    public String descripcion; // DESCRIPCION DEL PROBLEMA CON MAS DESARROLLO
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    // Constructor
    public Especialidad(String nombre) {
    }
}
