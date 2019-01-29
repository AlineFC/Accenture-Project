package cl.accenture.proyecto.controller;

import cl.accenture.proyecto.model.*;
import cl.accenture.proyecto.services.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/proyectos")
@CrossOrigin(origins = "*")
public class ProyectoController {

    private ProyectoService proyectoService;

    @Autowired   //constructor autorizado
    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    //permite agregar un nuevo proyecto, solo se puede hacer si se agrega con la id de la empresa
    @RequestMapping(method = RequestMethod.PUT, path = "/agregarProyecto")
    public boolean  agregarProyecto(@RequestBody Proyecto proyecto){
        if (proyectoService.encontrarNombre(proyecto.getNombre())){
            System.out.println("Este Proyecto ya  se encuentra registrado");
            return false;
        }
        else {
            proyectoService.agregarProyecto(proyecto);
            System.out.println("Guardado correctamente");
            return true;
        }
    }
    //permite encontrar todos los proyectos
    @RequestMapping(method = RequestMethod.GET, path = "/encontrarTodos")
    public List<Proyecto> encontrarTodos(){
        return proyectoService.encontrarTodos();
    }
    //permite obtener el proyecto segun su nombre
    @RequestMapping(method = RequestMethod.GET, path = "/obtenerNombre")
    public Proyecto obtenerNombre(@RequestBody Proyecto proyecto){
       Proyecto proyecto1=proyectoService.obtenerNombre(proyecto.getNombre());
        return proyecto1;
    }
    //permite eliminar un proyecto en la base de datos
    @RequestMapping(method = RequestMethod.DELETE, path = "/eliminarProyecto")
    public void eliminarProyecto(@RequestBody Proyecto proyecto){
        proyectoService.eliminarProyecto(proyecto);
    }

}


