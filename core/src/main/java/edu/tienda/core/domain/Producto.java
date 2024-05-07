package edu.tienda.core.domain;

import lombok.*;

@Getter
@NoArgsConstructor
@Setter
@ToString
@EqualsAndHashCode
public class Producto {
    private Integer id;
    private String nombre;
    private Double precio;
    private Integer stock;

    public Producto(Integer id, String nombre, Double precio, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

}
