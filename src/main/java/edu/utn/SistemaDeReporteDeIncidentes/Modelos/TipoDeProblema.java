package edu.utn.SistemaDeReporteDeIncidentes.Modelos;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;

@Entity
@Getter @Setter
@Table(name = "tipo_de_problema")
public class TipoDeProblema {
    @Id
    @Column(name = "id_tipo_de_problema")
    public int idTipoDeProblema;
    @Column(name = "nombre_tipo_de_problema")
    public String nombre;
    @Column(name = "descripcion")
    public String descripcion;
    @Column(name = "servicio_vinculado", nullable = false)
    public Servicio servicioVinculado;
    @Column(name = "max_tiempo_resolucion")
    public int maxTiempoDeResolucion; // TIEMPO MAXIMO DE RESOLUCION EN HORAS!
    @Column(name = "especialidades_que_mitigan")
    @OneToMany(mappedBy = "idEspecialidad")
    public LinkedList<Especialidad> especialidades; // ESPECIALIDADES QUE MITIGAN ESTE TIPO DE PROBLEMA!
}
