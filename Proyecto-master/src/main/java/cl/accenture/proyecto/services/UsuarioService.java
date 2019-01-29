package cl.accenture.proyecto.services;

import cl.accenture.proyecto.model.Usuario;
import cl.accenture.proyecto.repositorio.UsuarioRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //lo basico para agregar un Usuario, utiliza segun sea el caso inexistente, o existente
    public Usuario validacionUsuario(Usuario usuario) {
        Usuario usuario1 = new Usuario();
        List<Usuario> usuarios;
        usuarios = usuarioRepository.findByEmail( usuario.getEmail() );
        switch (usuarios.size()) {
            case 0:
                usuario1.setMensaje( "inexistente" );
                break;
            case 1:
                if (usuario.getContrasena().equals( usuarios.get( 0 ).getContrasena() )) {
                    usuario1.setMensaje( "Correcto" );
                } else {
                    usuario1.setMensaje( "contrasena" );
                }
                break;
        }
        return usuario1;
    }

    //el metodo del login, para que el controller se aplique
    public Usuario login(String email, String contrasena) {
        for(Usuario p:usuarioRepository.findAll()){
            if(p.getEmail().equals(email) && p.getContrasena().equals(contrasena)){
                return p;
            }
        }
        return null;
    }
    //metodo para encriptar
    public String encriptar(String contrasena) { String respuesta = DigestUtils.md5Hex(contrasena);return respuesta; }
    //obtener usuario por id
    public Usuario obtenerUsuario(String idUser){
        return usuarioRepository.findByIdUser(idUser);
    }
    //obtiene usuario por el nombre
    public Usuario obtenerNombre(String nombre){return  usuarioRepository.findByNombre(nombre);}
    public List<Usuario> obtenerEmail(String email){
        return usuarioRepository.findByEmail(email);
    }
    //encuentra todos los usuarios
    public List<Usuario> encontrarTodos(){
        List<Usuario> usuarios=new ArrayList<>();
        usuarioRepository.findAll().forEach(Usuario -> usuarios.add(Usuario));
        return usuarios;
    }
    //lo esencial para editar, agregar etc..
    public void guardarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }
    //elimina el usuario de la base de datos segun el id
    public void eliminarUser(Usuario usuario){usuarioRepository.delete(usuario);}

}
