package cisneros.qrarchivo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author JCISNEROS
 */
@Entity
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int antecedente;
    private String strTribunal;
    private int numExpediente;
    private int year;
    private String nombreUsuaria;
    private boolean estatusM;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private Time hora;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAntecedente() {
        return antecedente;
    }

    public void setAntecedente(int antecedente) {
        this.antecedente = antecedente;
    }

    public String getStrTribunal() {
        return strTribunal;
    }

    public void setStrTribunal(String strTribunal) {
        this.strTribunal = strTribunal;
    }

    public int getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(int numExpediente) {
        this.numExpediente = numExpediente;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getNombreUsuaria() {
        return nombreUsuaria;
    }

    public void setNombreUsuaria(String nombreUsuaria) {
        this.nombreUsuaria = nombreUsuaria;
    }

    public boolean isEstatusM() {
        return estatusM;
    }

    public void setEstatusM(boolean estatusM) {
        this.estatusM = estatusM;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }
}