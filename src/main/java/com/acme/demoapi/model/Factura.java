package com.acme.demoapi.model;

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.GeneratedValue;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numeroFactura;
    private String codigoCliente;
    private String nombreCliente;
    private String direccion;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaFactura;
    private BigDecimal totalFactura;
    @OneToMany(fetch = FetchType.LAZY)
    private List<DetalleFactura> detalleFacturas;  //Realizar conexión con DetalleFactura
}
