package com.acme.demoapi.integration.sunat.api;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.acme.demoapi.integration.sunat.dto.Invoice;
import com.acme.demoapi.model.Factura;

@Service
public class ComplianceAPI {

    private static final String URL_API_SUNAT="http://localhost:8081/api/sunat/";

    //NOTA DE ERROR: Cuando el error no esté marcado en rojo, es posible que se encuentre entre "" [Ejem: "//localhoost"]

    private RestTemplate restTemplate;  //RestTemplate son plantillas para conectar por medio de http a un servicio Rest

    public ComplianceAPI(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    //Toda la lógica se hace en la integración
    public void send(Factura facturaContoso){
        Invoice payload = new Invoice();
        payload.setFechaEmision(facturaContoso.getFechaFactura());
        payload.setImporte(facturaContoso.getTotalFactura());
        payload.setRucEmisor("999999999");
        payload.setRucComprador(facturaContoso.getCodigoCliente());

        HttpEntity<Invoice> bodyRequest = new HttpEntity<Invoice>(payload);
        ResponseEntity<Invoice> response = restTemplate.exchange(URL_API_SUNAT,
                                            HttpMethod.POST,
                                            bodyRequest,
                                            new ParameterizedTypeReference<Invoice>(){});
        facturaContoso.setNumeroFactura(response.getBody().getNumeroFactura());
    }

}
