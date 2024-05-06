package edu.tienda.core.controllers;

import edu.tienda.core.domain.Producto;
import edu.tienda.core.services.ProductoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoControllerRest {
    @Autowired
    private ProductoService productosService;
    @GetMapping
    public ResponseEntity<?> getProductos(){
        List<Producto> productos=productosService.getProductos();
        System.out.println(productos);
        return ResponseEntity.ok(productos);
    }
}
