package edu.tienda.core.controllers;

import edu.tienda.core.domain.Cliente;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping("/clientes")
public class ClienteRestController {
    private List<Cliente> clientes = new ArrayList<>(Arrays.asList(
            new Cliente("Lean","1234","Leandro"),
            new Cliente("Ale","1234","Alejandro"),
            new Cliente("Mar","1234","Mariano")
    ));
    @GetMapping
    public List<Cliente> getClientes(){
        return clientes;
    }
    @GetMapping("/{userName}")
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
    @PostMapping
    public Cliente altaCliente(@RequestBody Cliente cliente){
        clientes.add(cliente);
        return cliente;
    }
    @PutMapping
    public Cliente modificacionCiente(@RequestBody Cliente cliente){
        Cliente clienteEncontrado = clientes.stream().
                filter(cli -> cli.getUsername().equalsIgnoreCase(cliente.getUsername())).
                findFirst().orElseThrow();
        clienteEncontrado.setPassword(cliente.getPassword());
        clienteEncontrado.setNombre(cliente.getNombre());
        return clienteEncontrado;
    }
    @DeleteMapping("/{userName}")
    public void deleteCliente(@PathVariable String userName){
        Cliente clienteEncontrado = clientes.stream().
                filter(cli -> cli.getUsername().equalsIgnoreCase(userName)).
                findFirst().orElseThrow();
        clientes.remove(clienteEncontrado);
    }


}

