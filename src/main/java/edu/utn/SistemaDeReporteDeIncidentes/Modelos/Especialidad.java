package edu.utn.SistemaDeReporteDeIncidentes.Modelos;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "especialidades")
@Getter @Setter
@NoArgsConstructor
public class Especialidad {
    @Column(name = "tecnico", nullable = false)
    public PersonalTecnico tecnico;
    @Id
    @Column(name = "id_especialidad", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEspecialidad;
    @Column(name = "nombre_especialidad", nullable = false)
    public String nombre;
    @Column(name = "descripcion_especialidad")
    public String descripcion; // DESCRIPCION DEL PROBLEMA CON MAS DESARROLLO
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    // Constructor
    public Especialidad(String nombre) {
        this.nombre = nombre;
    }
}