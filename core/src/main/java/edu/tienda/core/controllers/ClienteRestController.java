package edu.tienda.core.controllers;

import edu.tienda.core.domain.Cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import edu.tienda.core.exceptions.ResourceNotFoundException;
@RestController
@RequestMapping("/clientes")
public class ClienteRestController {
    private List<Cliente> clientes = new ArrayList<>(Arrays.asList(
            new Cliente("Lean","1234","Leandro"),
            new Cliente("Ale","1234","Alejandro"),
            new Cliente("Mar","1234","Mariano")
    ));
    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes(){
        return ResponseEntity.ok(clientes);
    }
    @GetMapping("/{userName}")
    public ResponseEntity<?> getCliente(@PathVariable String userName){
        return clientes.stream().
                filter(cliente -> cliente.getUsername()
                        .equalsIgnoreCase(userName))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente" + userName + "No encontrado. "));

//        for (Cliente cli : clientes){
//            if (cli.getUsername().equalsIgnoreCase(userName)){
//                return cli;
//            }
//        }
//        return null;
    }
    @PostMapping
    public ResponseEntity<?> altaCliente(@RequestBody Cliente cliente){
        clientes.add(cliente);
       // return ResponseEntity.ok(cliente);

        //OBTENIENDO URL DEL SERVICIO
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userName}")
                .buildAndExpand(cliente.getUsername())
                .toUri();
        return  ResponseEntity.created(location).body(cliente);
    }
    @PutMapping
    public ResponseEntity<?> modificacionCiente(@RequestBody Cliente cliente){
        Cliente clienteEncontrado = clientes.stream().
                filter(cli -> cli.getUsername().equalsIgnoreCase(cliente.getUsername())).
                findFirst().orElseThrow();
        clienteEncontrado.setPassword(cliente.getPassword());
        clienteEncontrado.setNombre(cliente.getNombre());
        return ResponseEntity.ok(clienteEncontrado);
    }
    @DeleteMapping("/{userName}")
    public ResponseEntity deleteCliente(@PathVariable String userName){
        Cliente clienteEncontrado = clientes.stream().
                filter(cli -> cli.getUsername().equalsIgnoreCase(userName)).
                findFirst().orElseThrow();
        clientes.remove(clienteEncontrado);
        return ResponseEntity.noContent().build();
    }


}

