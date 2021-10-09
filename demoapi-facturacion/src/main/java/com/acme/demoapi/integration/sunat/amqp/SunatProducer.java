package com.acme.demoapi.integration.sunat.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.acme.demoapi.model.Factura;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SunatProducer {
    
    private AmqpTemplate amqpTemplate;
    @Value("${sunat.rabbitmq.exchange}")    //Valor desde application.properties, para el exchange de la integracion con rabbitmq
    private String EXCHANGE_NAME;

    public SunatProducer(AmqpTemplate amqpTemplate){
        this.amqpTemplate = amqpTemplate;
    }

    public void send(Factura facturaContoso){   //Recibe el parametro factura
        try{
            ObjectMapper mapper = new ObjectMapper();   //Crea un mapper hacia json
            String json = mapper.writeValueAsString(facturaContoso);    //convierte la factura a json que es un string
            amqpTemplate.convertSendAndReceive(EXCHANGE_NAME, "", json);    //Se envía el mensaje (json) a amqp
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }
    }
}
