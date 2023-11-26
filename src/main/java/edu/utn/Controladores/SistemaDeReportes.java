package edu.utn.Controladores;

import edu.utn.Interfaces.VistaInteractiva;
import org.apache.commons.cli.*;
import edu.utn.Interfaces.BuscarObjeto;
import edu.utn.Interfaces.ComprobarExistencia;
import edu.utn.Modelos.*;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Predicate;

import static java.lang.System.exit;

public class SistemaDeReportes implements VistaInteractiva {
    private static String argumento;
    public static void ejecutarEnInteractivo (){
        System.out.println(s + "Sistema de reporte de incidentes en ejecucion...");
        VistaInteractiva.construccionDeOpciones(); // CONSTRUYO LOS OBJETOS OPCION, QUE POSTERIORMENTE VAN A SER VINCULADOS A METODOS
        VistaInteractiva.mapearFunciones(); // ACÁ ES DONDE SE MAPEA UNA OPCION A UN METODO
        while (true) {
            System.out.print(p);
            argumento = scanner.nextLine(); // EL OPERADOR INGRESA COMANDOS
            VistaInteractiva.ejecucionDeComandos(argumento);
            if (argumento.equals("exit")) {
                try {
                    if (conexion != null) { conexion.close(); }
                    exit(0);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}