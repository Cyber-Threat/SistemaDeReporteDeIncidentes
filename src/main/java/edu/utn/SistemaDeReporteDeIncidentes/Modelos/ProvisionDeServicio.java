package edu.utn.SistemaDeReporteDeIncidentes.Modelos;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "subscripcion")
@Getter
@NoArgsConstructor
public class ProvisionDeServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_subscripcion", nullable = false)
    @JoinColumn(name = "")
    private int idSubscripcion;
    @Column(name = "cliente", nullable = false)
    @JoinColumn(name = "cliente", table = "clientes", referencedColumnName = "cuit")
    public PersonaCliente cliente;
    @ManyToOne
    @Column(name = "servicio", nullable = false)
    @JoinColumn(name = "servicio", referencedColumnName = "idServicio")
    public Servicio servicio;
    public PersonaCliente getCliente() { return cliente; }
}
