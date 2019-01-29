package cl.accenture.proyecto.repositorio;

import cl.accenture.proyecto.model.Proyecto;
import org.springframework.data.repository.CrudRepository;


//repositorios con parametros y bases para crear metodos necesarios

public interface ProyectoRepository extends CrudRepository<Proyecto, String> {


    @Override
    long count();

    Proyecto findByNombre(String nombre);
    //Proyecto findByStatus(String Vigente, String Vencido);



}