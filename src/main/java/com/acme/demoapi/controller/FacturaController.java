package com.acme.demoapi.controller;

import com.acme.demoapi.model.*;
import com.acme.demoapi.repository.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/factura", produces ="application/json")
public class FacturaController {

    private final FacturaRepository facturaData;

    public FacturaController(FacturaRepository factData){   //En este método está llamando al repositorio
        this.facturaData = factData;             //lo puse con nombres distintos para entender de donde son las variables
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Integer> create(@RequestBody Factura f){    //Lo que esta en paréntesis es el contrato (Hasta ahí le esta pasando la factura) | Metodo crea el número de la factura?
        facturaData.save(f);        //Graba en la tabla
        facturaData.flush();

        return new ResponseEntity<Integer>(f.getId(), HttpStatus.CREATED);  //Y devuelve el ID
    }

}
