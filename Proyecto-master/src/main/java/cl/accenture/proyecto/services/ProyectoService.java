package cl.accenture.proyecto.services;


import cl.accenture.proyecto.model.Proyecto;
import cl.accenture.proyecto.repositorio.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProyectoService {

    private static final Object Proyecto ="";
    private ProyectoRepository proyectoRepository;

    @Autowired
    public ProyectoService(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }
    //obtiene por nombre
    public Proyecto obtenerNombre(String nombre){
        return proyectoRepository.findByNombre(nombre);
    }

    /*public boolean statusProyecto(String Vigente, String Vencido){ return proyectoRepository.findByStatus(Vigente,Vencido);
    }

*/
    public boolean encontrarNombre(String nombre){
        return proyectoRepository.existsById(nombre);
    }

    //lo basico para crear un proyecto
    public void agregarProyecto(Proyecto proyecto){
        proyectoRepository.save(proyecto);
    }

   /*
    public void modificar(Proyecto Proyecto){
        proyectoRepository.save(proyectoRepository);
    }
*/  //encuentra Proyectos
   public List<Proyecto> encontrarTodos(){
       List<Proyecto> proyectos=new ArrayList<>();
       proyectoRepository.findAll().forEach(Proyecto -> proyectos.add(Proyecto));
       return proyectos;
   }
   //elimina el Proyecto
    public void eliminarProyecto(Proyecto proyecto){
        Proyecto proyecto1=new Proyecto(proyecto.getNombre(),proyecto.getInicio(),proyecto.getTermino(),proyecto.getstatus());
        proyectoRepository.delete(proyecto1);
    }

}
