package cl.accenture.proyecto.model;


import javax.persistence.*;

@Entity
@Table(name = "empleados")
public class Persona {

    @Id
    @Column(name = "Rut")
    private String idPersona;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Cargo")
    private String cargo;
    @Column(name = "Habilidad")
    private String habilidad;
    @Column(name = "Correo")
    private String email;

    private String mensaje;
    @ManyToOne
    @JoinColumn(name = "Proyecto")
    private Proyecto proyecto;

    public Persona() {

    } /*

    objeto de la empresa que en este caso seria empleado con todos sus atributos


*/
    public Persona(String email, String idPersona,String nombrePersona,String cargo,String habilidad,String mensaje,Proyecto proyecto) {
        this.idPersona = idPersona;
        this.nombre = nombrePersona;
        this.cargo=cargo;
        this.habilidad=habilidad;
        this.proyecto=proyecto;
        this.email=email;
        this.mensaje=mensaje;

             }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "idPersona='" + idPersona + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cargo='" + cargo + '\'' +
                ", habilidad='" + habilidad + '\'' +
                ", email='" + email + '\'' +
                ", proyecto=" + proyecto +
                '}';
    }
}