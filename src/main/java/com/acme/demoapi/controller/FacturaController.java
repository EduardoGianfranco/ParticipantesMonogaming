package com.acme.demoapi.controller;

import java.util.List;

import com.acme.demoapi.model.*;
import com.acme.demoapi.repository.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/factura", produces ="application/json")
public class FacturaController {

    private final FacturaRepository facturaData;
    private final DetalleFacturaRepository detalleFacturaData; 

    public FacturaController(FacturaRepository factData, DetalleFacturaRepository detalleFactData){   //En este método está llamando al repositorio
        this.facturaData = factData;             //lo puse con nombres distintos para entender de donde son las variables
        this.detalleFacturaData = detalleFactData;
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Integer> create(@RequestBody Factura f){    //Lo que esta en paréntesis es el contrato (Hasta ahí le esta pasando la factura) | Metodo crea el número de la factura?
        facturaData.save(f);        //Graba en la tabla
        facturaData.flush();        //Crea el id
        List<DetalleFactura> listItems = f.getDetalleFacturas();    //Sacar los detalles de la factura de la trama (CCNA?)
        listItems.stream().forEach(o -> o.setFactura(f));   //esos detalles se pasan a una lista, para actualizar elobjeto capturado por el método create
        detalleFacturaData.saveAllAndFlush(listItems);  //save y flush de los items de la factura

        return new ResponseEntity<Integer>(f.getId(), HttpStatus.CREATED);  //Y devuelve el id
    }

}
