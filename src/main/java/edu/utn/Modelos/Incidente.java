package edu.utn.Modelos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedList;

@Getter
@Setter
public class Incidente {
    private int id;
    private PersonaCliente cliente;
    private Servicio servicio;
    private PersonalTecnico tecnicoAsignado;
    private String descripcionProblema;
    private Problema problema;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaResolucion;
    private EstadoIncidente estado;
    private LinkedList<Comentario> comentarios; // SECCION DE COMENTARIOS
    // Constructor
    public Incidente(int id, PersonaCliente cliente, Servicio servicio, String descripcionProblema, Problema problema, LocalDateTime fechaCreacion, EstadoIncidente estado) {
        this.id = id;
        this.cliente = cliente;
        this.servicio = servicio;
        this.descripcionProblema = descripcionProblema;
        this.problema = problema;
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
    private void imprimirComentarios(){
        comentarios.stream().forEach(comentario -> { comentario.imprimirComentario(); });
    }
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
        System.out.println((fechaResolucion.equals(null)) ? "Sin resolver." :
                "Fecha de resolucion: " + fechaCreacion.toString());
        System.out.println(FormatoDeTexto.colores.blue + "\t└"
                + FormatoDeTexto.colores.reset + " Tipo de problema: " +
                this.problema.toString());
        System.out.println(FormatoDeTexto.colores.blue + "\t└"
                + FormatoDeTexto.colores.reset + " Descripcion del problema: " +
                this.descripcionProblema);
        System.out.println(FormatoDeTexto.colores.cyan + "◄► ◄► ◄► ◄► ◄► ◄► ◄► ◄► COMENTARIOS ◄► ◄► ◄► ◄► ◄► ◄► ◄► ◄►" + FormatoDeTexto.colores.reset);
        imprimirComentarios();
    }
    public LocalDateTime getFechaResolucion() { return fechaResolucion; }
    public void setFechaResolucion(LocalDateTime fechaResolucion) { this.fechaResolucion = fechaResolucion; }
    public EstadoIncidente getEstado() { return estado; }
    public void setEstado(EstadoIncidente estado) { this.estado = estado; }
}
