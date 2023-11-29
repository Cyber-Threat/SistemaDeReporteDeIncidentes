package edu.utn.SistemaDeReporteDeIncidentes.Interfaces;

import edu.utn.SistemaDeReporteDeIncidentes.Modelos.FormatoDeTexto;
import edu.utn.SistemaDeReporteDeIncidentes.Servicios.ServicioDeIncidentes;
import edu.utn.SistemaDeReporteDeIncidentes.Servicios.ServicioDePersonas;
import org.apache.commons.cli.*;
import org.jetbrains.annotations.NotNull;
import java.util.*;

public interface VistaInteractiva {
    Scanner scanner = new Scanner(System.in);
    Options opciones = new Options(); // LAS OPCIONES SON LOS COMANDOS QUE EL USUARIO PUEDE O NO EJECUTAR
    Map<String, Runnable> mapaDeFunciones = new HashMap<>(); // MAPA DONDE UNA OPCION ESTA VINCULADA A UN METODO!
    CommandLineParser parser = new DefaultParser(); // PARSER DE ARGUMENTOS DEL CLI
    static void construccionDeOpciones() {
        addOption("cs", "cargarServicio", "Carga de un servicio al sistema");
        addOption("cc", "cargarCliente", "Carga de un cliente al sistema");
        addOption("ct", "cargarTecnico", "Carga de un tecnico al sistema.");
        addOption("ci", "cargarIncidente", "Carga de un incidente al sistema.");
        addOption("is", "imprimirServicios", "Imprime los servicios de un cliente dado.");
        addOption("ic", "imprimirClientes", "Imprime los clientes registrados en el sistema.");
        addOption("it", "imprimirTecnicos", "Imprime los tecnicos registrados en el sistema.");
        addOption("ii", "imprimirIncidentes", "Imprime los servicios disponibles.");
        // addOption("db", "dbConfig", "Configuracion de acceso a la base de datos."); PARA HACER
        addOption("h", "help", "Muestra un mensaje de ayuda.");
        addOption("db", "conectarDb", "Conecta a la base de datos.");
    }
    private static void addOption(String shortName, String longName, String description) {
        Option option = new Option(shortName, longName, false, description);
        option.setRequired(false);
        opciones.addOption(option);
    }
    static void ejecucionDeComandos(@NotNull String args) {
        try {
            CommandLine cmd = parser.parse(opciones, args.split(" "));
            llamarFuncion(cmd);
        } catch (ParseException pe){
            System.out.println(FormatoDeTexto.iconos.error + "Error en la interpretacion de los comandos.");
            System.out.println(pe.getMessage());
        }
    }
    private static void llamarFuncion(@NotNull CommandLine cmd){
        for (Option opt: cmd.getOptions()){
            String command = opt.getOpt();
            // System.out.println(i + "Evaluando comando: " + command);
            if (mapaDeFunciones.containsKey(command)){
                mapaDeFunciones.get(command).run();
            } else {
                System.out.println(FormatoDeTexto.iconos.error + "No se pudo interpretar el comando: " + command);
            }
        }
    }
    static void mapearFunciones(){
        mapaDeFunciones.put("cc", () -> ServicioDePersonas.crearPersona(scanner, "cliente"));
        mapaDeFunciones.put("ct", () -> ServicioDePersonas.crearPersona(scanner, "tecnico"));
//        mapaDeFunciones.put("cs", () -> Servicios_DAO.cargarServicio(scanner));
        mapaDeFunciones.put("ci", () -> ServicioDeIncidentes.cargarIncidente());
//        mapaDeFunciones.put("is", () -> Personas_DAO.imprimirServicios(scanner));
        mapaDeFunciones.put("ic", () -> ServicioDePersonas.imprimirPersonas(ServicioDePersonas.clientes));
        mapaDeFunciones.put("it", () -> ServicioDePersonas.imprimirPersonas(ServicioDePersonas.tecnicos));
        mapaDeFunciones.put("ii", () -> ServicioDeIncidentes.imprimirIncidentes());
        mapaDeFunciones.put("h", () -> help());
    }
    // ▼ ▼ ▼ ▼ METODOS QUE EL OPERADOR DE LA MESA DE AYUDA PUEDE EJECUTAR ▼ ▼ ▼ ▼ ▼ ▼
    private static void help() {
        System.out.println(FormatoDeTexto.iconos.help + "Mensaje de ayuda: ");
    }


}
