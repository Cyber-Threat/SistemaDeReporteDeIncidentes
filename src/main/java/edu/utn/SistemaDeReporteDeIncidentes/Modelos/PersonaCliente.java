package edu.utn.SistemaDeReporteDeIncidentes.Modelos;
import javax.persistence.*;
//import edu.utn.SistemaDeReporteDeIncidentes.Interfaces.*;
//import edu.utn.SistemaDeReporteDeIncidentes.Interfaces.BuscarObjeto;
//import edu.utn.SistemaDeReporteDeIncidentes.Interfaces.ComprobarExistencia;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.function.Predicate;

@Entity
@Table(name = "clientes")
@Data @NoArgsConstructor
public class PersonaCliente extends Persona {
    public PersonaCliente(@NotNull RazonSocial tipo, @NotNull String nombreCompleto, @NotNull String cuit) {
        super(tipo, nombreCompleto, cuit);
        // this.servicios = new LinkedList<>();
    }
/*    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "cliente")
    private LinkedList<Servicio> servicios;
    public boolean existeServicio(Servicio s){
        Predicate<Servicio> predicado = servicio -> {
            return servicio.equals(s);
        };
        ComprobarExistencia<Servicio> comprobante = (laColeccion, elPredicado) -> {
            return laColeccion.stream().anyMatch(elPredicado);
        };
        return comprobante.comprobar(servicios, predicado);
    }
    public Servicio buscar_retornarServicio(Servicio s){
        Predicate<Servicio> predicado = servicio -> {
            return servicio.equals(s);
        };
        BuscarObjeto<Servicio> buscador = (laColeccion, elPredicado) -> {
            return laColeccion.stream().filter(elPredicado).findFirst().get();
        };
        return buscador.buscar(servicios, predicado);
    }
    public void imprimirServiciosDisponibles() {
        if (servicios.size() == 0) { System.out.println("No hay servicios cargados al momento de la ejecucion del comando."); return; }
        servicios.stream().forEach(
                servicio -> System.out.println("\tNombre legal de la empresa proveedora: " + servicio.getNombreLegal() + "\n"
                        + "\tNombre comercial de la empresa proveedora: " + servicio.getNombreComercial() + "\n"
                        + "\tNombre del servicio: " + servicio.getNombreDelServicio() + "\n")
        );
    }
    public void agregarServicio(Servicio s) throws Exception {
        if (existeServicio(s)){
            throw new Exception("El servicio que se busca agregar a la lista de servicios del cliente, ya existe.");
        } else {
            this.servicios.add(s);
        }
    }*/
}
