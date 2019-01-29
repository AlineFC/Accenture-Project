package cl.accenture.proyecto.model;


import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @Column(name = "idEmpresa")
    private String idEmpresa;

    public Empresa(){
    }

    /*

    objetos del sistema, este caso seria la empresa innefast
     */
    public Empresa(String idEmpresa, String nombreEmpresa ){
        this.idEmpresa=idEmpresa;
    }
    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "idEmpresa='" + idEmpresa + '\'' +
                '}';
    }
}
