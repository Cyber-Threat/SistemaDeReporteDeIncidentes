package edu.utn.SistemaDeReporteDeIncidentes.Controladores;

import edu.utn.SistemaDeReporteDeIncidentes.Interfaces.VistaInteractiva;
import edu.utn.SistemaDeReporteDeIncidentes.Modelos.*;
import static java.lang.System.exit;

public class SistemaDeReportes implements VistaInteractiva {
    private static String argumento;
    public static void ejecutarEnInteractivo (){
        System.out.println(FormatoDeTexto.iconos.success + "Sistema de reporte de incidentes en ejecucion...");
        VistaInteractiva.construccionDeOpciones(); // CONSTRUYO LOS OBJETOS OPCION, QUE POSTERIORMENTE VAN A SER VINCULADOS A METODOS
        VistaInteractiva.mapearFunciones(); // ACÁ ES DONDE SE MAPEA UNA OPCION A UN METODO
        while (true) {
            System.out.print(FormatoDeTexto.iconos.prompt);
            argumento = scanner.nextLine(); // EL OPERADOR INGRESA COMANDOS
            VistaInteractiva.ejecucionDeComandos(argumento);
            if (argumento.equals("exit")) {
                exit(0);
            }
        }
    }
}