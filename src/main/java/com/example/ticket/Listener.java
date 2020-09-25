package com.example.ticket;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
@Log4j2
@Component
public class Listener {

    @RabbitListener(queuesToDeclare = @Queue(name = "que01"))
    public void listen(String str){
        log.info("message {}",str);
    }
}
