package com.city.common.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

//发送消息
public class JmsSender {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(""+"tcp://192.168.56.1:61616");
        Connection connection = null;
        try {
            //创建连接
            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            //创建队列,如果队列已经存在则不会创建    first-queue 是队列名称
            //destination 目的地
            Destination destination = session.createQueue("first-queue");

            //创建消息发送者
            MessageProducer producer = session.createProducer(destination);

            TextMessage message = session.createTextMessage("hello activemq");
            //设置消息属性
            message.setStringProperty("messageProperty","我是属性内容");
            producer.send(message);

            //session.commit();
            session.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
