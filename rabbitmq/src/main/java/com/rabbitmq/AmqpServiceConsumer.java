package com.rabbitmq;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Message;

import java.io.IOException;

/**
 * @author: yangchun
 * @description:
 * @date: Created in 2019-09-02 16:48
 */

@Component
public class AmqpServiceConsumer {
    private Logger logger = LoggerFactory.getLogger(AmqpServiceConsumer.class);
    @Autowired
    private IAmqpService amqpHelloService;

    public AmqpServiceConsumer() {
    }

    @RabbitListener(queues = {"com.queue.notify.hello"})
    public void receiveSmsCodeQueue( Message message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) {
        this.logger.info("------hello：消费者处理消息------");
        this.amqpHelloService.receiveHelloQueue(new String(message.getBody()));
        try {
            channel.basicAck(deliveryTag,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
