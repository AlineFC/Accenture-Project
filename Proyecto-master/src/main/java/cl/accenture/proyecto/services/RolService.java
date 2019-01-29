package cl.accenture.proyecto.services;

import cl.accenture.proyecto.model.Rol;
import cl.accenture.proyecto.repositorio.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolService {

    private RolRepository rolRepository;

    @Autowired
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    //validacion para la creacion de un rol
    public Rol validacionRol(Rol rol) {
        Rol rol1 = new Rol();
        List<Rol> rols;
        rols = rolRepository.findBynombre(rol.getNombre());
        switch(rols.size()){
            case 0:
                rol1.setDescripcion("inexistente");
                break;
            case 1:
                if(rol.getIdRol().equals(rols.get(0).getIdRol())){
                    rol1.setDescripcion("Correcto");
                }else{
                    rol1.setDescripcion("contraseña");
                }
                break;
        }
        return rol1;
    }
    //obtiene id
    public Rol rolPorId(String idRol){
        return rolRepository.findByIdRol(idRol);
    }
    //public Rol rolPorDescripcion(String descripcion){return rolRepository.findBydescripcion(descripcion);}
    //obtiene por el nombre
    public List<Rol> rolPorNombre(String nombre){
        return rolRepository.findBynombre(nombre);
    }
    //encuentra todos los rol
    public List<Rol> encontrarTodos(){
        List<Rol> rols=new ArrayList<>();
        rolRepository.findAll().forEach(Rol -> rols.add(Rol));
        return rols;
    }
    //guarda
    public void guardarRol(Rol rol){
        rolRepository.save(rol);
    }
    //elimina
    public void eliminarRol(Rol rol){rolRepository.delete( (Rol) rol );}
}


