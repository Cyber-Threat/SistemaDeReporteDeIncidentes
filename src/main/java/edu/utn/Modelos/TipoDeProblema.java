package edu.utn.Modelos;

import java.util.LinkedList;

public class TipoDeProblema {
    public int id;
    public String nombre;
    public String descripcion;
    public int maxTiempoDeResolucion; // TIEMPO MAXIMO DE RESOLUCION EN HORAS!
    public LinkedList<Especialidad> especialidades; // ESPECIALIDADES QUE MITIGAN ESTE TIPO DE PROBLEMA!
}
