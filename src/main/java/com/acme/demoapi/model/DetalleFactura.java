package com.acme.demoapi.model;

import java.math.BigDecimal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_detalle_factura")
public class DetalleFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int cantidad;
    private BigDecimal precio;
    private String producto;
    @JsonIgnore     //Para solucionar error de redundancia al mostrar los detalles de la factura (Se hacía un bucle infinito)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factura_id")
    private Factura factura;
}
