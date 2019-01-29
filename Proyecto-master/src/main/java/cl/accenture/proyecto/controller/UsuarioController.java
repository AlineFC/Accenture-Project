package cl.accenture.proyecto.controller;
import cl.accenture.proyecto.model.Usuario;
import cl.accenture.proyecto.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    //login de acceso a usuario
    @RequestMapping(method = RequestMethod.POST, path = "login")
    public boolean login(@RequestBody Usuario usuario) {
        Usuario usuario1 = usuarioService.login( usuario.getEmail(), usuario.getContrasena() );
        if (usuario1 != null) {
            System.out.println( "iniciando sesion... " );
            return true;
        } else{
            System.out.println( "Usuario o contraseña incorrecta " );
            return false;
        }
    }
    //metodo para agregar un usuario, el usuario se agregara solo si tiene la id de la empresa, y la id de su rol, si no el sistema no se lo permitira
    @RequestMapping( method = RequestMethod.PUT, path = "/agregarUsuario")////funciona
    protected boolean agregarUs(@RequestBody Usuario usuario) {
        String destino = "";
        Usuario usuario1 = new Usuario();
        usuario1.setEmail(usuario.getEmail());
        usuario1.setContrasena(usuario.getContrasena());
        usuario1= usuarioService.validacionUsuario(usuario1);
        if (true) {
            switch (usuario1.getMensaje()) {
               case "Correcto":
                   // map.addAttribute("email", email);
                    destino = "El email, y la contraseña ya se encuentran registrados en la base de datos.";
                    break;
                case "inexistente":
                    //map.addAttribute( "El usuario no existe.");
                    destino = "Usuario creado";
                    Usuario us = new Usuario();
                    us.setIdUser(usuario.getIdUser());
                    us.setNombre(usuario.getNombre());
                    us.setEmail(usuario.getEmail());
                    us.setContrasena(usuario.getContrasena());
                    us.setRol(usuario.getRol());
                    usuarioService.guardarUsuario(us);
                    break;
            }
            return Boolean.parseBoolean(destino);
        }else{
            return false;
        }
    }
    //metodo para editar el uduario
    @RequestMapping( method = RequestMethod.PUT, path = "/editarUsuario")//funcionaaaaaaaaaaaaaaaaaaaaaaaa
    public boolean editUser(@RequestBody Usuario usuario) {
        Usuario usuario1 = usuarioService.obtenerUsuario(usuario.getIdUser());
        Assert.notNull(usuario, "Usuario not found");
        usuario1.setNombre(usuario.getNombre());
        usuario1.setContrasena(usuario.getContrasena());
        usuario1.setEmail(usuario.getEmail());
        usuario1.setRol(usuario.getRol());
        usuario1.setIdUser(usuario.getIdUser());
        usuarioService.guardarUsuario(usuario1);
        return Boolean.parseBoolean("Guardado.");
    }
    //solo ingresando lo que es la id del usuario, se podra eliminar de la base de datos

    @RequestMapping( method = RequestMethod.DELETE, path = "/eliminarUsuario")
    public boolean deleteUser(@RequestParam String idUser) {
        Usuario usuario = usuarioService.obtenerUsuario(idUser);
        System.out.println("Usuario DELETE" + usuario);
        usuarioService.eliminarUser(usuario);
        return true;
    }
    //metodo que obtiene el usuario solo por su id
    @RequestMapping( method = RequestMethod.GET, path = "/obtenerUsuario/{id}") //funciona
    public Usuario obtenerPorId(@PathVariable(value="id") String id) {
        return usuarioService.obtenerUsuario(id); }

        //encuentra el usuario solo por su email
   @RequestMapping(method = RequestMethod.GET, path = "/encontrarEmail")//funciona
    public List<Usuario> encontrarEmail(@RequestParam String email){ //funciona
        List<Usuario> usuarios=usuarioService.obtenerEmail(email);
        return usuarios;
    }
    //encuentra el usuario por su nombre
    @RequestMapping( method = RequestMethod.GET, path = "/encontrarNombre")//funciona
    public List<Usuario> encontrarNombre(@RequestParam String nombre){ //funciona
        List<Usuario> usuarios=usuarioService.obtenerEmail(nombre);
        return usuarios;
    }

    //obtiene todos los usuarios
    @RequestMapping(method = RequestMethod.GET, path = "/encontrarTodos") //funciona
    public  List<Usuario> encontrarTodos(){
        return usuarioService.encontrarTodos();
    }

}
