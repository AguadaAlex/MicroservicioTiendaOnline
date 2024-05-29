package edu.tienda.core.services;

import edu.tienda.core.domain.Producto;
import edu.tienda.core.persistance.entities.ProductoEntity;
import edu.tienda.core.persistance.entities.repositories.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("BD")
@ConditionalOnProperty(
        value = "productos.estrategia",
        havingValue = "EN_BD")
public class ProductosServiceBDImpl implements ProductoService{
    @Autowired
    private ProductosRepository productosRepository;
    public List<Producto> getProductos(){
        List<ProductoEntity> productoEntities=productosRepository.findAll();
        List<Producto> productos= new ArrayList<>();
        for (ProductoEntity productoEntity : productoEntities){
            Producto producto = new Producto();
            producto.setId(productoEntity.getId());
            producto.setNombre(productoEntity.getNombre());
            producto.setPrecio(productoEntity.getPrecio());
            producto.setStock(productoEntity.getStock());
            productos.add(producto);
        }
        return productos;
    }
}
