package cl.accenture.proyecto.controller;


import cl.accenture.proyecto.model.Rol;
import cl.accenture.proyecto.services.NotificationService;
import cl.accenture.proyecto.services.RolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administradores")
@CrossOrigin(origins = "*")
public class RolController {

    private RolService rolService;

    //lo esencial para el email
    @Autowired
    private NotificationService notificationService;

    @Autowired
    public RolController(RolService rolService){
        this.rolService=rolService;
    }

    private Logger logger = LoggerFactory.getLogger(RolController.class);

    //obtiene el administrador segun el nombre, eje: administrador rr.hh, administrador de finanzas etc..
    @RequestMapping(method = RequestMethod.GET, path = "/administradores/{nombre}")
    public List<Rol> rolPorNombre(@RequestParam String nombre){
        return rolService.rolPorNombre(nombre);
    }


    //agrega un nuevo administrador..
    @RequestMapping(method = RequestMethod.PUT, path = "/agregarRol")//funciona
    protected boolean agregarRol(@RequestBody Rol rol) {
        String destino = "";
        Rol rol1 = new Rol();
        rol1.setIdRol(rol.getIdRol());
        rol1.setNombre(rol.getNombre());
        rol1 = rolService.validacionRol(rol1);
        if (true) {
            switch (rol1.getDescripcion()) {
                case "Correcto":
                   // map.addAttribute("idRol", idRol);
                    destino = "Este administrador  ya se encuentra registrado en la base de datos.";
                    break;
                case "inexistente":
                   // map.addAttribute( "El rol no existe.");
                    destino = "Rol creado";
                    Rol rl = new Rol();
                    rl.setIdRol(rol.getIdRol());
                    rl.setNombre(rol.getNombre());
                    rl.setDescripcion(rol.getDescripcion());
                    rolService.guardarRol(rl);
                    break;
            }
            return Boolean.parseBoolean(destino);
        }else{
            return true;
        }
    }

    //elimina un administrador de la base de datos
    @RequestMapping(method = RequestMethod.DELETE, path = "/eliminarRol") //funciona
    public boolean deleteRol(@RequestParam String id) {
        Rol rol = rolService.rolPorId(id);
        System.out.println("Rol DELETE" + rol);
        rolService.eliminarRol(rol);
    try {
    notificationService.sendNotificacion( "A sido liberado", rol );
    }
      catch (MailException m){
          logger.info("Error enviando en mensaje.. " + m.getMessage());
      }
        return true;
    }

    //obtiene un administrador por su id
    @RequestMapping(method = RequestMethod.GET,path = "/obtenerRol/{id}") //funciona
    public Rol obtenerPorId(@PathVariable(value="id") String id) {
        return rolService.rolPorId(id); }
        //obtiene el administrador por su nombre
    @RequestMapping(method = RequestMethod.GET, path = "/encontrarNombre")//funciona
    public List<Rol> encontrarNombre(@RequestParam String nombre){ //funciona
        List<Rol> rols=rolService.rolPorNombre(nombre);
        return rols;
    }
    //encuentra todos los administradores en la base de datos

    @RequestMapping(method = RequestMethod.GET, path = "/encontrarTodos") //funciona
    public  List<Rol> encontrarTodos(){
        return rolService.encontrarTodos();
    }

}
