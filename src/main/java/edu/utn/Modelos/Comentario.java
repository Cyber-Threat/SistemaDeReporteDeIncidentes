package edu.utn.Modelos;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Comentario {
    @Id
    public int idIncidente; // IDENTIFICACION UNIVOCA DEL INCIDENTE AL QUE HACE REFERENCIA!
    @ManyToOne
    public Persona persona; // PERSONA QUE DEJO EL COMENTARIO, DEJO EL OBJETO COMO ATRIBUTO
    // PORQUE PRETENDO IMPRIMIR SI LA PERSONA ES TECNICO, CLIENTE, O EMPRESA! ASI COMO
    // TAMBIÉN SU NOMBRE COMPLETO, PERO A LA HORA DE GUARDAR EL COMENTARIO EN LA BASE DE DATOS SOLO
    // GUARDO EL CUIT DE LA PERSONA QUE HIZO EL COMENTARIO!
    public Date fecha; // FECHA Y HORA EN LA QUE SE HIZO EL COMENTARIO!
    public String contenido; // CONTENIDO DEL COMENTARIO EN SI!
    public void imprimirComentario(){
        System.out.print(FormatoDeTexto.iconos.info
                + FormatoDeTexto.colores.blue + fecha.toString() + FormatoDeTexto.colores.reset
                + ": " + persona.getNombreCompleto()
                + " (" + persona.getCuit().getDigitos() + ") "
                + "("  + FormatoDeTexto.colores.green
                + ((persona instanceof PersonaCliente) ? "Cliente": "Tecnico") + FormatoDeTexto.colores.reset
                + ")");
        System.out.println("\t" + contenido);
    }
    /*public int getIdIncidente() { return idIncidente; }
    public void setIdIncidente(int idIncidente) { this.idIncidente = idIncidente; }*/
}
