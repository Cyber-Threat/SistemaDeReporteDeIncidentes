package edu.utn.SistemaDeReporteDeIncidentes.Modelos;
import javax.persistence.*;

@Entity
public class Notificacion {
    @Id
    private int id;
    public String titulo;
    public String cuerpo;
    public Incidente incidenteVinculado;
    private boolean notificacionEnviada;
}
