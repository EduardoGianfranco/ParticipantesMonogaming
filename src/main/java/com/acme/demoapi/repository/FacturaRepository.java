package com.acme.demoapi.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import com.acme.demoapi.model.*;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer>{ //Recibe Factura y el ID?
    
}
