package com.acme.demoapi.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import com.acme.demoapi.model.*;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Integer>{ //El grabado fue repartido en 2 partes debido al error en 1:57:00 de la clase 3
    
}
