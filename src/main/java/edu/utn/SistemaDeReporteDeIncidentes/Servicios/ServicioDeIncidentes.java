package edu.utn.SistemaDeReporteDeIncidentes.Servicios;

import edu.utn.SistemaDeReporteDeIncidentes.Modelos.FormatoDeTexto;
import edu.utn.SistemaDeReporteDeIncidentes.Modelos.Incidente;

import java.util.LinkedList;

public class ServicioDeIncidentes {
    public static LinkedList<Incidente> incidentes = new LinkedList<Incidente>();
    // LOS METODOS PARA OPERAR CON LA LISTA DE FORMA LOCAL
    public static void imprimirIncidentes () {
    }

    public static void cargarIncidente () {
        System.out.println(FormatoDeTexto.iconos.actionNeeded + "Cargue los datos de un incidente...");
    }

    // LOS METODOS QUE USA EL DAO PARA ACCEDER A LA BASE DE DATOS USANDO LA CONEXION DEL JDBC
}