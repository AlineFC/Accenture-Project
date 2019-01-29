package cl.accenture.proyecto.controller;


import cl.accenture.proyecto.model.Empresa;
import cl.accenture.proyecto.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
@CrossOrigin(origins = "*")
public class EmpresaController {

    private EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }


    //metodo que permite enocntrar todas las empresas, pero en este caso es solo una InneFast
    @RequestMapping(method = RequestMethod.GET, path = "/encontrarTodos") //funciona
    public  List<Empresa> encontrarTodos(){
        return empresaService.encontrarTodos();
    }


}
