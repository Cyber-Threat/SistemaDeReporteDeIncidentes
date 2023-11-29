package edu.utn.SistemaDeReporteDeIncidentes.Modelos;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "problemas")
@Getter @Setter
@NoArgsConstructor
public class Problema {
    @Id
    @Column(name = "id_problema")
    public int id_problema; // IDENTIFICACION UNIVOCA PARA GUARDAR EN LA BASE DE DATOS
    @Column(name = "tipo_de_problema", nullable = false)
    public TipoDeProblema tipoDeProblema;
    @Column(name = "nombre_problema", nullable = false)
    public String nombre;
    @Column(name = "descripcion_problema", nullable = false)
    public String descripcion;
    @Column(name = "tiempo_estimado_resolucion", nullable = false)
    public LocalDate tiempoEstimadoDeResolucion;
    @Column(name = "fecha_de_resolucion")
    private LocalDate fechaResolucion;
    @Column(name = "consideraciones_de_cierre")
    public String consideracionesDeCierre;
    // Constructor
    public Problema(String nombre, String especialidad) {
        this.nombre = nombre;
    }
    public LocalDate calcularTiempoEstimadoDeResolucion() {
        return tiempoEstimadoDeResolucion;
    }
}