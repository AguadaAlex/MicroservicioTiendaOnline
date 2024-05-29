package edu.tienda.core.controllers;

import edu.tienda.core.configurations.ConfigurationParameters;
import edu.tienda.core.domain.Producto;
import edu.tienda.core.services.ProductoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoControllerRest {
    @Autowired
    @Lazy
    private ProductoService productosService;

    @Autowired
    private ConfigurationParameters configurationParameters;
    @GetMapping
    public ResponseEntity<?> getProductos(){
        System.out.println("Parameters: "+ configurationParameters.toString());
        List<Producto> productos=productosService.getProductos();
        return ResponseEntity.ok(productos);
    }
    @GetMapping("/fake-productos")
    public ResponseEntity<?> fakeProductosAPI(){
        List<Producto> productos = new ArrayList<>(Arrays.asList(
           new Producto(1,"Camiseta Juventus", 1200.0, 4),
           new Producto(2,"Camiseta River Plate", 1000.0, 8),
           new Producto(3,"Camiseta Boca Junior", 900.0, 1)
        ));
        return ResponseEntity.ok(productos);
    };
    @PostMapping
    public ResponseEntity<?> altaProducto(@RequestBody Producto producto){
        productosService.saveProducto(producto);
        //OBTENIENDO URL DE SERVICIO
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(producto.getId())
                .toUri();
        return ResponseEntity.created(location).body(producto);
    }
}
