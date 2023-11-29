package edu.utn.SistemaDeReporteDeIncidentes.Modelos;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.Objects;

@Entity
@Table(name = "servicio")
@Getter @Setter
@NoArgsConstructor
public class Servicio {
    @Id
    @Column(name = "id_servicio", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idServicio;
    @Column(name = "nombre_legal", nullable = false)
    private String nombreLegal;
    @Column(name = "nombre_comercial", nullable = false)
    private String nombreComercial;
    @Column(name = "nombre_del_servicio", nullable = false)
    private String nombreDelServicio;
    @OneToMany(mappedBy = "servicioVinculado")
    private LinkedList<TipoDeProblema> problemas;
    public String getNombreLegal() { return nombreLegal; }
    public String getNombreComercial() { return nombreComercial; }
    public String getNombreDelServicio() { return nombreDelServicio; }
    public Servicio(String nombreLegal, String nombreComercial, String nombreDelServicio){
        this.nombreLegal = nombreLegal;
        this.nombreComercial = nombreComercial;
        this.nombreDelServicio = nombreDelServicio;
    }
    boolean equals(@NotNull Servicio s){
        return (this.nombreLegal.equals(s.getNombreLegal())
            && this.nombreComercial.equals(s.getNombreComercial())
            && this.getNombreDelServicio().equals(s.getNombreDelServicio()));
    }
    public int getIdServicio() { return idServicio; }
}