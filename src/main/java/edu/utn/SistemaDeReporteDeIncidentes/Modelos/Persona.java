package edu.utn.SistemaDeReporteDeIncidentes.Modelos;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@MappedSuperclass
@NoArgsConstructor
public abstract class Persona implements Serializable {
    @Column(name = "razon_social", nullable = false)
    public RazonSocial razonSocial;
    @Column(name = "nombre", nullable = false)
    public String nombreCompleto;
    @Column(name = "fecha_de_nacimiento", nullable = false)
    private LocalDate fechaDeNacimiento;
    @Column(name = "num_de_telefono", nullable = true)
    public String numDeTelefono;
    @Column(name = "email", nullable = true)
    public String email;
    @Id
    @Column(name = "cuit", length = 11, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Cuit cuit;
    @Column(name = "medio_de_contacto")
    public MedioDeContacto medioDeContacto;
    public enum MedioDeContacto{
        Email, WhatsApp, Telegram, Facebook, LinkedIn
    }
    public enum RazonSocial {
        juridica, fisica
    }
    public static class Cuit { // CODIGO UNICO DE IDENTIFICACION TRIBUTARIA
        private String digitos;
        private String tipoGlobal;
        private String idUnica; // DNI, O PUEDE SER NUMERO DE EMPRESA
        private char digitoVerificador;
        public Cuit (@NotNull String dato) { // SE PUEDE INTRODUCIR EL CUIT CON GUIONES Y CON PUNTOS PERO IGUALMENTE SE VAN A EXTRAER LOS DIGITOS Y CONTROLAR QUE SEAN 11 DIGITOS!
            dato = dato.replace(".", "");
            if (dato.contains("-")) {
                this.tipoGlobal = dato.split("-")[0];
                this.idUnica = dato.split("-")[1];
            } else {
                this.tipoGlobal = dato.substring(0, 2);
                this.idUnica = dato.substring(2, 10);
            }
            this.digitoVerificador = dato.charAt(dato.length() - 1);
            this.digitos = tipoGlobal + idUnica + digitoVerificador;
            //  PARA HACER: LANZAR EXCEPTION SI EL CUIT NO TIENE 11 DIGITOS O CONTIENE CARACTERES INVALIDOS!
            if (digitos.length() != 11) { // LANZA EXCEPTION
            }
        }
        public String getTipoGlobal() { return tipoGlobal; }
        public String getIdUnica() { return idUnica; }
        public char getDigitoVerificador() { return digitoVerificador; }
        public String getDigitos() { return digitos; }
    }
    public Persona(@NotNull Persona.RazonSocial tipo, @NotNull String nombreCompleto, @NotNull String cuit) {
        this.razonSocial = tipo;
        this.cuit = new Cuit(cuit);
        this.nombreCompleto = nombreCompleto;
    }
    public String getNombreCompleto() { return this.nombreCompleto; }
    public Cuit getCuit() { return this.cuit; }
    public RazonSocial getRazonSocial() {
        return razonSocial;
    }
    public LocalDate getFechaDeNacimiento() { return fechaDeNacimiento; }
    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) { this.fechaDeNacimiento = fechaDeNacimiento; }
}