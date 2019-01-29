package cl.accenture.proyecto.services;


import cl.accenture.proyecto.model.Persona;
import cl.accenture.proyecto.model.Proyecto;
import cl.accenture.proyecto.repositorio.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PersonaService {

    private PersonaRepository personaRepository;

    @Autowired
    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }
/*

validacion para agregar a la persona posterioriormente por el controller, que el email no se repita

*/
    public Persona validacionPersona(Persona persona) {
        Persona per = new Persona();
        List<Persona> personas;
        personas = personaRepository.findByEmail(persona.getEmail());
        switch(personas.size()){
            case 0:
                per.setMensaje("inexistente");
                break;
            case 1:
                if(persona.getEmail().equals(personas.get(0).getEmail())){
                    per.setMensaje("Correcto");
                }else{
                    per.setMensaje("contraseña");
                }
                break;
        }
        return per;
    }

    /*

obtiene la persona por su id

*/
    public Persona obtenerPersona(String idPersona){
        return personaRepository.findByIdPersona(idPersona);
    }
    /*

obtiene el proyecto en el cual esta signada la persona
*/
    public List<Persona> obtenerProyecto(Proyecto proyecto){return  personaRepository.findByProyecto(proyecto);}

    /*

obtiene la persona por el email
*/
    public List<Persona> obtenerEmail(String email){
        return personaRepository.findByEmail(email);
    }

    /*

encuentra toda la lista de empleados
*/
    public List<Persona> encontrarTodos(){
        List<Persona> personas=new ArrayList<>();
        personaRepository.findAll().forEach(Persona -> personas.add(Persona));
        return personas;
    }

    /*

guarda la persona, despues servira para agregar, y editar en el controller
*/
    public void guardarPersona(Persona persona){
        personaRepository.save(persona);
    }

    /*

elimina la persona por medio de su id, que en este caso seria su rut
*/
    public void eliminarPersona(Persona persona){personaRepository.delete(persona);}

}
