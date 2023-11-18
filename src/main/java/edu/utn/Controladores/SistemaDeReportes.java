package edu.utn.Controladores;

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

public class SistemaDeReportes {
    // IRRELEVANTE, ESTOS ATRIBUTOS TIENEN EL SOLO PROPOSITO DE DARLE UN POCO DE FORMATO AL TEXTO!
    private static final String s = FormatoDeTexto.iconos.success;
    private static final String i = FormatoDeTexto.iconos.info;
    private static final String a = FormatoDeTexto.iconos.actionDone;
    private static final String n = FormatoDeTexto.iconos.actionNeeded;
    private static final String e = FormatoDeTexto.iconos.error;
    private static final String h = FormatoDeTexto.iconos.help;
    private static final String w = FormatoDeTexto.iconos.warning;
    private static final String p = FormatoDeTexto.iconos.prompt;
    // ESTOS ATRIBUTOS SI SON RELEVANTES PARA EL FUNCIONAMIENTO!
    private static Scanner scanner = new Scanner(System.in);
    private static LinkedList<Incidente> incidentes = new LinkedList<Incidente>();
    private static LinkedList<PersonaCliente> clientes = new LinkedList<PersonaCliente>();
    private static LinkedList<PersonalTecnico> tecnicos = new LinkedList<PersonalTecnico>();
    private static LinkedList<Solucion> soluciones = new LinkedList<Solucion>();
    // ESTOS ATRIBUTOS QUE SIGUEN SON PARA PODER INGRESAR COMANDOS Y EJECUTAR METODOS!
    private static final Options opciones = new Options(); // LAS OPCIONES SON LOS COMANDOS QUE EL USUARIO PUEDE O NO EJECUTAR
    private static final Map<String, Runnable> mapaDeFunciones = new HashMap<>(); // MAPA DONDE UNA OPCION ESTA VINCULADA A UN METODO!
    private static final CommandLineParser parser = new DefaultParser(); // PARSER DE ARGUMENTOS DEL CLI
    // ESTE ATRIBUTO ES IMPORTANTE PORQUE CONTROLA EL FLUJO DEL PROGRAMA
    private static String args;
    // CONEXION A LA DB
    private static Connection conexion = null;
    public static void ejecutar (){
        System.out.println(s + "Sistema de reporte de incidentes en ejecucion...");
        construccionDeOpciones(); // CONSTRUYO LOS OBJETOS OPCION, QUE POSTERIORMENTE VAN A SER VINCULADOS A METODOS
        mapearFunciones(); // ACÁ ES DONDE SE MAPEA UNA OPCION A UN METODO
        while (true) {
            System.out.print(p);
            args = scanner.nextLine(); // EL OPERADOR INGRESA COMANDOS
            ejecucionDeComandos(args);
            if (args.equals("exit")) {
                try {
                    if (conexion != null) {conexion.close();}
                    exit(0);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
    private static void construccionDeOpciones() {
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
    private static void ejecucionDeComandos(@NotNull String args) {
        try {
            CommandLine cmd = parser.parse(opciones, args.split(" "));
            llamarFuncion(cmd);
        } catch (ParseException pe){
            System.out.println(e + "Error en la interpretacion de los comandos.");
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
                System.out.println(e + "No se pudo interpretar el comando: " + command);
            }
        }
    }
    private static void mapearFunciones(){
        mapaDeFunciones.put("cc", () -> crearPersona("cliente"));
        mapaDeFunciones.put("ct", () -> crearPersona("tecnico"));
        mapaDeFunciones.put("cs", () -> cargarServicio());
        mapaDeFunciones.put("ci", () -> cargarIncidente());
        mapaDeFunciones.put("is", () -> imprimirServicios());
        mapaDeFunciones.put("ic", () -> imprimirPersonas(clientes));
        mapaDeFunciones.put("it", () -> imprimirPersonas(tecnicos));
        mapaDeFunciones.put("ii", () -> imprimirIncidentes());
        mapaDeFunciones.put("h", () -> help());
        mapaDeFunciones.put("db", () -> conectarDb());
    }
    private static <T extends Persona > boolean existePersona (String cuit, Collection < T > coleccion){
        Predicate<T> predicado = cliente -> {
            return (
                    cliente.getCuit().getDigitos().equals(cuit)
            );
        };
        ComprobarExistencia<T> comprobante = (laColeccion, elPredicado) -> {
            return laColeccion.stream().anyMatch(elPredicado);
        };
        boolean existe = comprobante.comprobar(coleccion, predicado);
        if (existe) System.out.println(s + "El cliente <" + cuit + "> existe.");
        if (!existe) System.out.println(e + "El cliente <" + cuit + "> no existe.");
        return existe;
    }
    private static <T extends Persona > T buscarPersona(String cuit, Collection < T > coleccion) {
        Predicate<T> predicado = cliente -> {
            return cliente.getCuit().getDigitos().equals(cuit);
        };
        BuscarObjeto<T> buscador = (laColeccion, elPredicado) -> {
            return laColeccion.stream().filter(elPredicado).findFirst().get();
        };
        T personaBuffer = buscador.buscar(coleccion, predicado);
        return personaBuffer;
    }
    // ▼ ▼ ▼ ▼ METODOS QUE EL OPERADOR DE LA MESA DE AYUDA PUEDE EJECUTAR ▼ ▼ ▼ ▼ ▼ ▼
    private static void help() {
        System.out.println(h + "Mensaje de ayuda: ");
    }
    private static void imprimirServicios() {
        System.out.print(n + "Porfavor ingresar el CUIT de la persona que desea cargarle el servicio: ");
        String cuit = scanner.nextLine();
        if (existePersona(cuit, clientes)) {
            System.out.println(i + "Buscando cliente por CUIT..." + " " + cuit);
            buscarPersona(cuit, clientes).imprimirServiciosDisponibles();
        } else {
            System.out.println(e + "Parece que el cliente no existe.");
        }
    }
    private static void crearPersona (@NotNull String registro) {
        System.out.print(n + "Nombre completo de la persona: ");
        String nombre = scanner.nextLine();
        System.out.print(n + "CUIT de la persona: ");
        String cuit = scanner.next();
        System.out.print(n + "Persona juridica o persona fisica? <juridica> o <fisica>: ");
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
    private static <T extends Persona > void imprimirPersonas (@NotNull Collection < T > coleccion) {
        coleccion.stream().forEach(persona ->
                System.out.println(i + "Nombre completo: " + persona.nombreCompleto
                        + "\n\t CUIT: " + persona.getCuit().getDigitos()
                        + "\n\t Registrado como: " + ((persona instanceof PersonaCliente) ? "Cliente." : "Tecnico."))
        );
    }
    private static void imprimirIncidentes () {
    }
    private static void cargarServicio (){
        System.out.print(n + "Porfavor ingresar el CUIT de la persona que desea cargarle el servicio: ");
        String cuit = scanner.nextLine();
        if (existePersona(cuit, clientes)) {
            try {
                System.out.println(n + "Cargue los datos de un servicio...");
                System.out.print(n + "Nombre legal de la empresa proveedora del servicio: ");
                String nombreLegal = scanner.nextLine();
                System.out.print(n + "Nombre comercial de la empresa proveedora del servicio: ");
                String nombreComercial = scanner.nextLine();
                System.out.print(n + "Nombre para el servicio: ");
                String nombre = scanner.nextLine();
                buscarPersona(cuit, clientes).agregarServicio(
                        new Servicio(
                                nombreLegal,
                                nombreComercial,
                                nombre
                        )
                );
            } catch (Exception exc) {
                System.out.println(e + exc.getMessage());
                System.out.println(exc.fillInStackTrace());
            }
        } else {
            System.out.println(e + "Parece que el cliente " + cuit + " no existe.");
        }
    }
    private static void cargarIncidente () {
        System.out.println(n + "Cargue los datos de un incidente...");
    }
    private static void conectarDb(){ conexion = JdbcConnector.getConnection(); }
}