package edu.utn.SistemaDeReporteDeIncidentes.Modelos;
import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import java.util.LinkedList;
import java.util.Scanner;

@Entity
@Getter @Setter
@Table(name = "personal_tecnico")
@NoArgsConstructor
public class PersonalTecnico extends Persona{
    @OneToMany(mappedBy = "tecnico", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "especialidades")
    private LinkedList<Especialidad> especialidades = new LinkedList<>();
    @Column(name = "disponible")
    private boolean disponible;
    public PersonalTecnico(@NotNull RazonSocial tipo, @NotNull String nombreCompleto, @NotNull String cuit) {
        super(tipo, nombreCompleto, cuit);
    }
    public void agregarEspecialidades(@NotNull Scanner scanner){
        System.out.println("Ingrese las especialidades del tecnico, " +
                "la lectura de datos finaliza cuando se ingresa una cadena vacia.");
        String nombreEspecialidad;
        while (true) {
            nombreEspecialidad = scanner.nextLine();
            if (nombreEspecialidad.equals("")) {
                break;
            }
            this.especialidades.add(new Especialidad(nombreEspecialidad));
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
}