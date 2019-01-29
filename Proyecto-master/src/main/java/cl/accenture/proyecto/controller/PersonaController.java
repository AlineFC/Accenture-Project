package cl.accenture.proyecto.controller;


import cl.accenture.proyecto.model.Persona;
import cl.accenture.proyecto.model.Proyecto;

import cl.accenture.proyecto.services.NotificationService;
import cl.accenture.proyecto.services.PersonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
@CrossOrigin(origins = "*")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @Autowired //para el email
    private NotificationService notificationService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }
    private Logger logger = LoggerFactory.getLogger(RolController.class);

    //metodo para agregar una persona en la base de datos (empleado)
    @RequestMapping( method = RequestMethod.PUT, path = "/agregarUsuario")////funciona
    protected boolean agregarPer(@RequestBody Persona persona) {
        String destino = "";
        Persona persona1 = new Persona();
        persona1.setEmail(persona.getEmail());
        persona1.setIdPersona(persona.getIdPersona());
        persona1 = personaService.validacionPersona(persona1); //se valida conb el validador del service
        if (true) {
            switch (persona1.getMensaje()) {
                case "Correcto":
                    destino = "El email, y la contraseña ya se encuentran registrados en la base de datos.";
                    break;
                case "inexistente":
                    destino = "Usuario creado";
                    Persona per = new Persona();
                    per.setIdPersona(persona.getIdPersona());
                    per.setNombre(persona.getNombre());
                    per.setEmail(persona.getEmail());
                    per.setCargo(persona.getCargo());
                    per.setHabilidad(persona.getCargo());
                    per.setProyecto(persona.getProyecto());
                    personaService.guardarPersona(per);
                    break;
            }
            try { //se envio una notificacion al correo inmediatamente despues d eejecutar el metodo
                notificationService.sendNotificacionA( "El rut " + persona.getIdPersona() + " a sido agregada a proyecto " + persona.getProyecto() + " de forma exitosa.", persona );
            }
            //excepciones en caso de fallo envio email
            catch (MailException m){
                logger.info("Error enviando el mensaje.. " + m.getMessage());
            } //
            return Boolean.parseBoolean(destino);
        }else{
            return false;
        }
    }
        // metodo para editar, funcionaba bien, que fue hecho a base del metodo del usuario y el rol, pero no se que paso despues
   // pero funcionaba bien
    @RequestMapping( method = RequestMethod.PUT, path = "/editarUsuario")//funcionaaaaaaaaaaaaaaaaaaaaaaaa
    public boolean editPersona(@RequestBody Persona persona) {
        Persona persona1 = personaService.obtenerPersona(persona.getIdPersona());
        Assert.notNull(persona, "Persona not found");
        persona1.setNombre(persona.getNombre());
        persona1.setEmail(persona.getEmail());
        persona1.setCargo(persona.getCargo());
        persona1.setIdPersona(persona.getIdPersona());
        persona1.setHabilidad(persona.getCargo());
        persona1.setProyecto(persona.getProyecto());
        personaService.guardarPersona(persona1);
        return Boolean.parseBoolean("Guardado.");
    }


    //metodo para eliminar una persona, del proyecto y la base de datos
    @RequestMapping( method = RequestMethod.DELETE, path = "/eliminarPersona") //funciona
    public boolean deletePersona(@RequestParam String idPersona) {
        Persona persona = personaService.obtenerPersona(idPersona);
        System.out.println("Persona DELETE" + persona);
        personaService.eliminarPersona(persona);
        try {
            //envia un correo electronico notificando el metodo y accion que se realizo
            notificationService.sendNotificacionD( "El rut " + persona.getIdPersona() + " a sido liberado del proyecto " + persona.getProyecto() + " de forma exitosa. ", persona );
        }
        catch (MailException m){
            logger.info("Error enviando el mensaje.. " + m.getMessage());
        }
        return true;
    }
    //obtiene empleado por id que seria el rut
    @RequestMapping( method = RequestMethod.GET, path = "/obtenerUsuario/{id}") //funciona
    public Persona obtenerPorId(@PathVariable(value="id") String id) {
        return personaService.obtenerPersona(id); }

        //obtiene por email
    @RequestMapping(method = RequestMethod.GET, path = "/encontrarEmail")//funciona
    public List<Persona> encontrarEmail(@RequestParam String email){ //funciona
        List<Persona> personas=personaService.obtenerEmail(email);
        return personas;
    }//obtiene segun el nombre
    @RequestMapping( method = RequestMethod.GET, path = "/encontrarNombre")//funciona
    public List<Persona> encontrarNombre(@RequestParam Proyecto proyecto){ //funciona
        List<Persona> personas=personaService.obtenerProyecto(proyecto);
        return personas;
    }
    //los encuentra todos
    @RequestMapping(method = RequestMethod.GET, path = "/encontrarTodos") //funciona
    public  List<Persona> encontrarTodos(){
        return personaService.encontrarTodos();
    }

}
