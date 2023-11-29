package edu.utn.SistemaDeReporteDeIncidentes.Modelos;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "solucion")
@Getter
@NoArgsConstructor
public class Solucion { // MAPA DE PROBLEMAS Y LA ESPECIALIDAD QUE LO SOLUCIONA!
    @Id
    @Column(name = "idSolucion")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    @Column(name = "especialidad")
    public Especialidad especialidad;
    @Column(name = "tipo_de_problema")
    public TipoDeProblema tipoDeProblema;
    public Solucion(Especialidad e, TipoDeProblema p){
        this.especialidad = e;
        this.tipoDeProblema = p;
    }
}