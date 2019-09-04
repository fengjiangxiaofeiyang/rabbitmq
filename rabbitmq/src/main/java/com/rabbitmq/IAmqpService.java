package com.rabbitmq;

/**
 * @author: yangchun
 * @description:
 * @date: Created in 2019-09-02 16:53
 */
public interface IAmqpService {
    void convertAndSend(String message);
    void receiveHelloQueue(String message);
}
