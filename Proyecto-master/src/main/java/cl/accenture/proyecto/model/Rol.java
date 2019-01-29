/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.accenture.proyecto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author CrateX
 */
@Entity
@Table(name="administradores")
public class Rol {

    /*

    objeto del sistema que en este caso seria rol con todos sus atributos

    relacion manyToOne con el objeto usuario, un usuario tiene un rol, ya que esta pagina solo
    la manejan administradores


*/

    @Id
    @Column(name="idRol")
    private String idRol;

    @Column(name ="nombre" )
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    public Rol(){
    }


    public Rol(String idRol, String nombre, String descripcion) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.descripcion=descripcion;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "id=" + idRol +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
