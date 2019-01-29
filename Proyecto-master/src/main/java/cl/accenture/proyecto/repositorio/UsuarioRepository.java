package cl.accenture.proyecto.repositorio;

import cl.accenture.proyecto.model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//repositorios con parametros y bases para crear metodos necesarios

public interface UsuarioRepository extends CrudRepository<Usuario, String>{




    List<Usuario> findByEmail(String email);
    Usuario findByNombre(String nombre);
    Usuario findByIdUser(String idUser);
    Usuario findByEmailAndContrasena(String email, String contrasena);
}
