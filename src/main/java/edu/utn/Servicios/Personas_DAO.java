package edu.utn.Servicios;

import edu.utn.Interfaces.BuscarObjeto;
import edu.utn.Interfaces.ComprobarExistencia;
import edu.utn.Modelos.FormatoDeTexto;
import edu.utn.Modelos.Persona;
import edu.utn.Modelos.PersonaCliente;
import edu.utn.Modelos.PersonalTecnico;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.function.Predicate;

public class Personas_DAO {
    public static LinkedList<PersonaCliente> clientes = new LinkedList<PersonaCliente>();
    public static LinkedList<PersonalTecnico> tecnicos = new LinkedList<PersonalTecnico>();
    public static <T extends Persona> boolean existePersona (String cuit, Collection< T > coleccion){
        Predicate<T> predicado = cliente -> {
            return (
                    cliente.getCuit().getDigitos().equals(cuit)
            );
        };
        ComprobarExistencia<T> comprobante = (laColeccion, elPredicado) -> {
            return laColeccion.stream().anyMatch(elPredicado);
        };
        boolean existe = comprobante.comprobar(coleccion, predicado);
        if (existe) System.out.println(FormatoDeTexto.iconos.success + "El cliente <" + cuit + "> existe.");
        if (!existe) System.out.println(FormatoDeTexto.iconos.error + "El cliente <" + cuit + "> no existe.");
        return existe;
    }
    public static <T extends Persona > T buscarPersona(String cuit, Collection < T > coleccion) {
        Predicate<T> predicado = cliente -> {
            return cliente.getCuit().getDigitos().equals(cuit);
        };
        BuscarObjeto<T> buscador = (laColeccion, elPredicado) -> {
            return laColeccion.stream().filter(elPredicado).findFirst().get();
        };
        T personaBuffer = buscador.buscar(coleccion, predicado);
        return personaBuffer;
    }
    public static void imprimirServicios(Scanner scanner) {
        System.out.print(FormatoDeTexto.iconos.actionNeeded + "Porfavor ingresar el CUIT de la persona que desea cargarle el servicio: ");
        String cuit = scanner.nextLine();
        if (existePersona(cuit, clientes)) {
            System.out.println(FormatoDeTexto.iconos.info + "Buscando cliente por CUIT..." + " " + cuit);
            buscarPersona(cuit, clientes).imprimirServiciosDisponibles();
        } else {
            System.out.println(FormatoDeTexto.iconos.error + "Parece que el cliente no existe.");
        }
    }
    public static void crearPersona (Scanner scanner, @NotNull String registro) {
        System.out.print(FormatoDeTexto.iconos.actionNeeded + "Nombre completo de la persona: ");
        String nombre = scanner.nextLine();
        System.out.print(FormatoDeTexto.iconos.actionNeeded + "CUIT de la persona: ");
        String cuit = scanner.next();
        System.out.print(FormatoDeTexto.iconos.actionNeeded + "Persona juridica o persona fisica? <juridica> o <fisica>: ");
        String razonSocial = scanner.next();
        if (registro.equals("cliente")) {
            cargarPersona(new PersonaCliente((razonSocial.equals("juridica")) ? Persona.RazonSocial.juridica : Persona.RazonSocial.fisica,
                    nombre,
                    cuit));
            return;
        }
        cargarPersona(new PersonalTecnico((razonSocial.equals("juridica")) ? Persona.RazonSocial.juridica : Persona.RazonSocial.fisica,
                nombre,
                cuit));
    }
    private static <T extends Persona > void cargarPersona (T type){
        if (type instanceof PersonaCliente) {
            clientes.add((PersonaCliente) type);
            return;
        }
        tecnicos.add((PersonalTecnico) type);
    }
    public static <T extends Persona > void imprimirPersonas (@NotNull Collection < T > coleccion) {
        coleccion.stream().forEach(persona ->
                System.out.println(FormatoDeTexto.iconos.info + "Nombre completo: " + persona.nombreCompleto
                        + "\n\t CUIT: " + persona.getCuit().getDigitos()
                        + "\n\t Registrado como: " + ((persona instanceof PersonaCliente) ? "Cliente." : "Tecnico."))
        );
    }

    // LOS METODOS QUE ACCEDEN A LA BASE DE DATOS

}