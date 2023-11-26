package edu.utn.Modelos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedList;

@Getter
@Setter
public class Incidente {
    // ID LONG PK
    private int id;
    // ID LONG FK (El cliente que reporta el incidente)
    private PersonaCliente cliente;
    // ID LONG FK
    private PersonalTecnico tecnicoAsignado;
    // ID LONG FK
    private Servicio servicio;
    private String titulo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaDeCierre;
    private EstadoIncidente estado;
    private LinkedList<Problema> problemas;
    private String descripcionProblema;
    private boolean consideradoComplejo;
    private LinkedList<Comentario> comentarios; // SECCION DE COMENTARIOS
    // Constructor
    public Incidente(int id, PersonaCliente cliente, Servicio servicio, String descripcionProblema, Problema problema, LocalDateTime fechaCreacion, EstadoIncidente estado) {
        this.id = id;
        this.cliente = cliente;
        this.servicio = servicio;
        this.descripcionProblema = descripcionProblema;
        this.problemas.add(problema);
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
    private void imprimirComentarios(){ comentarios.stream().forEach(comentario -> { comentario.imprimirComentario(); }); }
    public void imprimirIncidente(){
        System.out.println(FormatoDeTexto.iconos.info + "Informacion del incidente...");
        System.out.println(FormatoDeTexto.colores.blue + "\t└"
                + FormatoDeTexto.colores.reset + " Cliente: "
                + this.cliente.getNombreCompleto());
        System.out.println(FormatoDeTexto.colores.blue + "\t└"
                + FormatoDeTexto.colores.reset + " Cuit del cliente:"
                + this.cliente.getCuit().getDigitos());
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
        this.problemas.stream().forEach(problema -> {
            System.out.println(FormatoDeTexto.iconos.info + "Tipo de problema: " + problema.nombre);
            System.out.println(FormatoDeTexto.iconos.info + "Especialidad que puede mitigar el problema: " + problema.especialidad);
            System.out.println(FormatoDeTexto.iconos.info + "Tiempo estimado de resolucion: " + problema.tiempoEstimadoDeResolucion.toString());
            System.out.println(FormatoDeTexto.iconos.info + "Servicio vinculado: " + problema.idServicioVinculado.toString());
        });
        System.out.println(FormatoDeTexto.colores.blue + "\t└"
                + FormatoDeTexto.colores.reset + " Descripcion del problema: " +
                this.descripcionProblema);
        System.out.println(FormatoDeTexto.colores.cyan + "◄► ◄► ◄► ◄► ◄► ◄► ◄► ◄► COMENTARIOS ◄► ◄► ◄► ◄► ◄► ◄► ◄► ◄►" + FormatoDeTexto.colores.reset);
        imprimirComentarios();
    }
    public void agregarProblema(Problema p){ this.problemas.add(p); }
}
