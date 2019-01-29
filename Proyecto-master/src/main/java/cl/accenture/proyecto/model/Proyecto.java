package cl.accenture.proyecto.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "proyectos")
public class Proyecto {


    @Id
    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Inicio")
    private String inicio;
    @Column(name = "Termino")
    private String  termino;
    @Column(name = "Status")
    private String status;

    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "idEmpresa")
    private Empresa empresa;


    /*
     lo que uno la id de la empresa con el proyecto, una relacion de manytoOne
     ej: UNA EMPRESA CONTIENE PROYECTOS, Y UN PROYECTO CONTIENE PERSONAS
    objeto de la empresa que en este caso seria empleado con todos sus atributos


*/

    public Proyecto(String nombre, String inicio, String termino, String status, String mensaje, Empresa empresa) {
        this.nombre = nombre;
        this.inicio = dateToDate(new Date());
        this.termino = dateToDate(new Date());
        this.status = status;
        this.mensaje = mensaje;
        this.empresa = empresa;
    }

    public Proyecto(String nombre, String inicio, String termino, String status) {

    }

    public Proyecto() {


    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio, Date parse) {
        this.inicio = inicio;
    }

    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino, Date parse) {
        this.termino = termino;
    }

    public String getstatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String dateToDate(Date date){
        DateFormat formatoFecha =new SimpleDateFormat("yyyy-MM-dd");
        return formatoFecha.format(date);
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "nombre='" + nombre + '\'' +
                ", inicio='" + inicio + '\'' +
                ", termino='" + termino + '\'' +
                ", status='" + status + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", empresa=" + empresa +
                '}';
    }


}

