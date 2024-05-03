package edu.tienda.core.controllers;

import edu.tienda.core.domain.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@RestController
public class ClienteRestController {
    private List<Cliente> clientes = new ArrayList<>(Arrays.asList(
            new Cliente("Lean","1234","Leandro"),
            new Cliente("Ale","1234","Alejandro"),
            new Cliente("Mar","1234","Mariano")
    ));
    @GetMapping("/clientes")
    public List<Cliente> getClientes(){
        return clientes;
    }
    @GetMapping("/clientes/{userName}")
    public Cliente getCliente(@PathVariable String userName){
        return clientes.stream().
                filter(cliente -> cliente.getUsername().equalsIgnoreCase(userName)).
                findFirst().orElseThrow();

//        for (Cliente cli : clientes){
//            if (cli.getUsername().equalsIgnoreCase(userName)){
//                return cli;
//            }
//        }
//        return null;
    }


}

