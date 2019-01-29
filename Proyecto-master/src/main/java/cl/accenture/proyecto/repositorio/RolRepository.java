package cl.accenture.proyecto.repositorio;

import cl.accenture.proyecto.model.Rol;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//repositorios con parametros y bases para crear metodos necesarios

public interface RolRepository extends CrudRepository<Rol, String> {


    List<Rol> findBynombre(String Nombre);
    Rol findByIdRol(String id);

}
