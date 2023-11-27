package edu.utn.Modelos;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

@Setter
@Getter
@Entity
@Table(name = "Persona")
public class PersonalTecnico extends Persona{
    @OneToMany
    @Column(name = "especialidades")
    private LinkedList<Especialidad> especialidades = new LinkedList<>();
    private LocalDate tiempoEstimadoDeResolucion;
    private boolean disponible;
    public PersonalTecnico(@NotNull RazonSocial tipo, @NotNull String nombreCompleto, @NotNull String cuit) {
        super(tipo, nombreCompleto, cuit);
    }
    protected PersonalTecnico(){}
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
    public LocalDate calcularTiempoEstimadoDeResolucion() {
        return tiempoEstimadoDeResolucion;
    }
}
