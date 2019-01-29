package cl.accenture.proyecto.services;


import cl.accenture.proyecto.model.Empresa;
import cl.accenture.proyecto.model.Proyecto;
import cl.accenture.proyecto.repositorio.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService{
    private EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

        //base del metodo que se ocupara en controller que sera encontrar todas las empresas
        public List<Empresa> encontrarTodos(){
        List<Empresa> empresas=new ArrayList<>();
        empresaRepository.findAll().forEach(Empresa -> empresas.add(Empresa));
        return empresas;
    }

}
