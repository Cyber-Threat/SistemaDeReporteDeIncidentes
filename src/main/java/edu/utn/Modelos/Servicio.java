package edu.utn.Modelos;

import org.jetbrains.annotations.NotNull;

public class Servicio {
    public int idUnivoca;
    private String nombreLegal; // NOMBRE LEGAL DE LA EMPRESA PROVEEDORA DEL SERVICIO
    private String nombreComercial; // NOMBRE COMERCIAL DE LA EMPRESA PROVEEDORA DEL SERVICIO
    private String nombreDelServicio; // NOMBRE DEL SERVICIO EN SI MISMO
    public String getNombreLegal() { return nombreLegal; }
    public String getNombreComercial() { return nombreComercial; }
    public String getNombreDelServicio() { return nombreDelServicio; }
    public Servicio(String nombreLegal, String nombreComercial, String nombreDelServicio){
        this.nombreLegal = nombreLegal;
        this.nombreComercial = nombreComercial;
        this.nombreDelServicio = nombreDelServicio;
    }
    boolean equals(@NotNull Servicio s){
        return (this.nombreLegal.equals(s.getNombreLegal())
            && this.nombreComercial.equals(s.getNombreComercial())
            && this.getNombreDelServicio().equals(s.getNombreDelServicio()));
    }
    public int getIdUnivoca() { return idUnivoca; }
}