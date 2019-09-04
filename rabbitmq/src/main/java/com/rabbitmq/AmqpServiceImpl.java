package com.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: yangchun
 * @description:
 * @date: Created in 2019-09-02 16:53
 */
@Service
public class AmqpServiceImpl implements IAmqpService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Override
    public void convertAndSend(String message) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId("abc");
        Message message1 = MessageBuilder.withBody(message.getBytes()).build();
        rabbitTemplate.convertAndSend("yang.update",message1,correlationData);
    }

    @Override
    public void receiveHelloQueue(String message) {
        System.out.println("message:"+message);
    }
}
