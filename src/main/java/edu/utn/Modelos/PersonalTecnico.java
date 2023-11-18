package edu.utn.Modelos;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.Scanner;

public class PersonalTecnico extends Persona{
    private LinkedList<String> especialidades = new LinkedList<>();
    private Float tiempoEstimadoDeResolucion;
    public PersonalTecnico(@NotNull RazonSocial tipo, @NotNull String nombreCompleto, @NotNull String cuit) {
        super(tipo, nombreCompleto, cuit);
    }
    public void agregarEspecialidades(@NotNull Scanner scanner){
        System.out.println("Ingrese las especialidades del tecnico, " +
                "la lectura de datos finaliza cuando se ingresa una cadena vacia.");
        String especialidad;
        while (true) {
            especialidad = scanner.nextLine();
            if (especialidad.equals("")) {
                break;
            }
            this.especialidades.add(especialidad);
        }
    }
    public void imprimirEspecialidades(){
        System.out.println("Especialidades del tecnico "+ FormatoDeTexto.colores.green + this.nombreCompleto + ": ");
        this.especialidades.stream().forEach(
            s -> {
                System.out.println(FormatoDeTexto.iconos.info + s);
            }
        );
    }
    public Float getTiempoEstimadoDeResolucion() {
        return tiempoEstimadoDeResolucion;
    }
    public void setTiempoEstimadoDeResolucion(Float tiempoEstimadoDeResolucion) {
        this.tiempoEstimadoDeResolucion = tiempoEstimadoDeResolucion;
    }
}
