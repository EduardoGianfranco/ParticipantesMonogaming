package com.acme.demoapi.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.*;

import com.acme.demoapi.model.*;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Integer>{ //El grabado fue repartido en 2 partes debido al error en 1:57:00 de la clase 3
    @Query(value = "SELECT o FROM DetalleFactura o WHERE o.factura=?1") //Query para extraer los items de la factura
    List<DetalleFactura> findItemsByFactura(Factura factura);    //Como son varios items, se usa un List y no un Optional
}   //Con este método sacamos los detalles de la factura
