package com.acme.demoapi.controller;

import java.util.*;
import com.acme.demoapi.model.*;
import com.acme.demoapi.repository.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.acme.demoapi.integration.sunat.amqp.SunatProducer;
import com.acme.demoapi.integration.sunat.api.*;

@RestController
@RequestMapping(value = "api/factura", produces ="application/json")
public class FacturaController {

    private final FacturaRepository facturaData;
    private final DetalleFacturaRepository detalleFacturaData;
    private final ComplianceAPI complianceApi;
    private final SunatProducer sunatProducer;

    public FacturaController(FacturaRepository factData, DetalleFacturaRepository detalleFactData,
                             ComplianceAPI complianceApi, SunatProducer sunatProducer){   //En este método está llamando al repositorio
        this.facturaData = factData;             //lo puse con nombres distintos para entender de donde son las variables
        this.detalleFacturaData = detalleFactData;
        this.complianceApi = complianceApi;
        this.sunatProducer = sunatProducer;
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <String> create(@RequestBody Factura f){    //Lo que esta en paréntesis es el contrato (Hasta ahí le esta pasando la factura) | Metodo crea el número de la factura?
        facturaData.save(f);        //Graba en la tabla
        facturaData.flush();        //Crea el id
        Factura generada = f;       //Error solucionaddo y explicado en video semana 3 en 2:10:00
        List<DetalleFactura> listItems = f.getDetalleFacturas();    //Sacar los detalles de la factura de la trama (CCNA?)
        listItems.stream().forEach(o -> o.setFactura(generada));   //esos detalles se pasan a una lista, para actualizar elobjeto capturado por el método create
        detalleFacturaData.saveAllAndFlush(listItems);  //save y flush de los items de la factura

        //complianceApi.send(generada);     //Utilizado para la integración fuerte (Síncrona)
        sunatProducer.send(generada);       //Utilizado para la integración débil (Asíncrona)

        return new ResponseEntity<String>(generada.getNumeroFactura(), HttpStatus.CREATED);  //Y devuelve el id
    }

    @GetMapping(value = "/{numeroFactura}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Factura> findByNumber(@PathVariable String numeroFactura){
        
        Optional<Factura> optFactura = facturaData.findByNumero(numeroFactura);
        if(optFactura.isPresent()){ //Si la factura esta presente en el Optional...
            Factura factura = optFactura.get(); //Se obtiene la factura
            List<DetalleFactura> detalleFacturas = detalleFacturaData.findItemsByFactura(factura); //Se obtienen los detalle (items)s de la factura
            factura.setDetalleFacturas(detalleFacturas);
            return new ResponseEntity<Factura>(factura, HttpStatus.OK);
        }else{
            return new ResponseEntity<Factura>(HttpStatus.NOT_FOUND);
        }
    }
}
