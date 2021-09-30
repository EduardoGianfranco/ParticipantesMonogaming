package com.acme.demoapi.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import com.acme.demoapi.model.*;

import java.util.*;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer>{ //Recibe Factura y el ID?
    @Query(value = "SELECT o FROM Factura o WHERE o.numeroFactura=?1")  //Query a ejecutar para realizar búsqueda
    Optional<Factura> findByNumero(String numeroFactura);   //Devolvemos la <factura> seleccionada por el método findByNumero
}
