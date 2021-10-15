package gob.sunat.apisunat.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {     //Aquí se realiza la configuración para la cola (Queue)
    
    @Value("${sunat.rabbitmq.exchange}")
    private String EXCHANGE_NAME;

    @Value("${sunat.rabbitmq.queue}")
    private String QUEUE_NAME;

    @Bean
    public Queue createQueue(){     //Aqui se crea la cola
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Exchange fanoutExchange(){   //En caso caiga aqui, se envia un fanout para mensajes alternativos?
        return new FanoutExchange(EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding queueBinding(){  //Aquí se hace el enlace Exchange con Cola [El camino (Exchange) enlaza a la cola (Queue)]
        return new Binding(QUEUE_NAME, Binding.DestinationType.QUEUE, EXCHANGE_NAME, "", null);
    }
}
