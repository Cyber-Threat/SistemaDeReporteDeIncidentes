package edu.utn.Servicios;

import edu.utn.Modelos.FormatoDeTexto;
import edu.utn.Modelos.Servicio;

import java.util.LinkedList;
import java.util.Scanner;

public class Servicios_DAO {
    private static LinkedList<Servicio> servicios = new LinkedList<>();
    // LOS METODOS PARA TRABAJAR CON LOS OBJETOS LOCALMENTE
    public static void cargarServicio (Scanner scanner){
        System.out.print(FormatoDeTexto.iconos.actionNeeded + "Porfavor ingresar el CUIT de la persona que desea cargarle el servicio: ");
        String cuit = scanner.nextLine();
        if (Personas_DAO.existePersona(cuit, Personas_DAO.clientes)) {
            try {
                System.out.println(FormatoDeTexto.iconos.actionNeeded + "Cargue los datos de un servicio...");
                System.out.print(FormatoDeTexto.iconos.actionNeeded + "Nombre legal de la empresa proveedora del servicio: ");
                String nombreLegal = scanner.nextLine();
                System.out.print(FormatoDeTexto.iconos.actionNeeded + "Nombre comercial de la empresa proveedora del servicio: ");
                String nombreComercial = scanner.nextLine();
                System.out.print(FormatoDeTexto.iconos.actionNeeded + "Nombre para el servicio: ");
                String nombre = scanner.nextLine();
                Personas_DAO.buscarPersona(cuit, Personas_DAO.clientes).agregarServicio(
                        new Servicio(
                                nombreLegal,
                                nombreComercial,
                                nombre
                        )
                );
            } catch (Exception exc) {
                System.out.println(FormatoDeTexto.iconos.error + exc.getMessage());
                System.out.println(exc.fillInStackTrace());
            }
        } else {
            System.out.println(FormatoDeTexto.iconos.error + "Parece que el cliente " + cuit + " no existe.");
        }
    }

    // LOS METODOS PARA ACCEDER A LA BASE DE DATOS

}