package edu.utn.SistemaDeReporteDeIncidentes.Modelos;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.LinkedList;

@Entity
@Getter @Setter
@Table(name = "incidentes")
@NoArgsConstructor
public class Incidente {
    // ID LONG PK
    @Id
    @Column(name = "id_incidente", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIncidente;
    // ID LONG FK
    @OneToOne
    @JoinColumn(referencedColumnName = "cuit", table = "personal_tecnico")
    private PersonalTecnico tecnicoAsignado;
    @Column(name = "subscripcion_vinculada", nullable = false)
    public ProvisionDeServicio provisionDeServicioVinculada;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "fecha_de_creacion")
    private LocalDateTime fechaCreacion;
    @Column(name = "fecha_de_cierre")
    private LocalDateTime fechaDeCierre;
    @Column(name = "estado")
    private EstadoIncidente estado;
    @Column(name = "descripcion_problema")
    private String descripcionProblema;
    @Column(name = "considerado_complejo")
    private boolean consideradoComplejo;
    @Column(name = "comentarios")
    private LinkedList<Comentario> comentarios; // SECCION DE COMENTARIOS
    @Column(name = "problemas")
    @OneToMany(mappedBy = "id_problema")
    private LinkedList<Problema> problemas;
    // Constructor
    public Incidente(ProvisionDeServicio s, String descripcionProblema, LocalDateTime fechaCreacion, EstadoIncidente estado) {
        this.provisionDeServicioVinculada = s;
        this.descripcionProblema = descripcionProblema;
        this.problemas = new LinkedList<>();
        this.comentarios = new LinkedList<>();
        this.fechaCreacion = LocalDateTime.now();
        this.estado = EstadoIncidente.ABIERTO;
    }
    public void asignarTecnico(PersonalTecnico tecnico) {
        this.tecnicoAsignado = tecnico;
        this.estado = EstadoIncidente.EN_PROCESO;
        notificarCliente("El incidente ha sido asignado a un técnico y está en proceso de resolucion.");
    }
    public void marcarComoResuelto(String comentarios) {
        this.estado = EstadoIncidente.RESUELTO;
        notificarCliente("El incidente ha sido resuelto. Comentarios: " + comentarios);
    }
    private void notificarCliente(String mensaje) { } //  PARA HACER!
    private void notificarTecnico(String mensaje) { } //  PARA HACER!
    private void imprimirComentarios(){ comentarios.forEach(Comentario::imprimirComentario); }
    public void imprimirIncidente(){
        System.out.println(FormatoDeTexto.iconos.info + "Informacion del incidente...");
        System.out.println(FormatoDeTexto.colores.blue + "\t└"
                + FormatoDeTexto.colores.reset + " Cliente: "
                + this.provisionDeServicioVinculada.getCliente().getNombreCompleto());
        System.out.println(FormatoDeTexto.colores.blue + "\t└"
                + FormatoDeTexto.colores.reset + " Cuit del cliente:"
                + this.provisionDeServicioVinculada.getCliente().getCuit().getDigitos());
        System.out.println(FormatoDeTexto.colores.blue + "\t└"
                + FormatoDeTexto.colores.reset + " Tecnico asignado: "
                + this.tecnicoAsignado.getNombreCompleto());
        System.out.println(FormatoDeTexto.colores.blue + "\t└"
                + FormatoDeTexto.colores.reset + " Cuit del tecnico: " +
                this.tecnicoAsignado.getCuit().getDigitos());
        System.out.println(FormatoDeTexto.colores.blue + "\t└"
                + FormatoDeTexto.colores.reset + " Fecha de creacion del Incidente: " +
                this.fechaCreacion.toString());
        System.out.println(FormatoDeTexto.colores.blue + "\t└"
                + FormatoDeTexto.colores.reset + " Tipos de problemas: ");
        this.problemas.forEach(problema -> {
            System.out.println(FormatoDeTexto.iconos.info + "Tipo de problema: " + problema.nombre);
            System.out.println(FormatoDeTexto.iconos.info + "Tiempo estimado de resolucion: " + problema.tiempoEstimadoDeResolucion.toString());
            System.out.println(FormatoDeTexto.iconos.info + "Tipo de problema: " + problema.tipoDeProblema.getNombre());
        });
        System.out.println(FormatoDeTexto.colores.blue + "\t└"
                + FormatoDeTexto.colores.reset + " Descripcion del problema: " +
                this.descripcionProblema);
        System.out.println(FormatoDeTexto.colores.cyan + "◄► ◄► ◄► ◄► ◄► ◄► ◄► ◄► COMENTARIOS ◄► ◄► ◄► ◄► ◄► ◄► ◄► ◄►" + FormatoDeTexto.colores.reset);
        imprimirComentarios();
    }
    public void agregarProblema(Problema p){ this.problemas.add(p); }
}
