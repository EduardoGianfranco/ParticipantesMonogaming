package gob.sunat.apisunat.integration;

import org.springframework.stereotype.Service;

import gob.sunat.apisunat.model.Invoice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.Message;

@Service
public class FacturaConsumer {

    @RabbitListener(queues = "${sunat.rabbitmq.queue}") //Entrar a la cola de factura(Invoice) | Asignar el nombre de app.properties (No necesariamente igual al de api facturación)
    public void receiveMessage(Message message){    //Recibir un mensaje de la cola (queue)
        String json = new String(message.getBody(), StandardCharsets.UTF_8);
        try{
            ObjectMapper mapper = new ObjectMapper();
            Invoice invoice = mapper.readValue(json, Invoice.class);
            System.out.println("Invoice: " + invoice);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
