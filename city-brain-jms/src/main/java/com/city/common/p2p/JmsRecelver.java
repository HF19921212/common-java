package com.city.common.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

//消息接收
public class JmsRecelver {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(""+"tcp://192.168.56.1:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("first-queue");

            //创建消息接收者
            MessageConsumer consumer = session.createConsumer(destination);

            TextMessage textMessage = (TextMessage) consumer.receive();
            System.out.println(textMessage.getText()+"=>"+textMessage.getStringProperty("messageProperty"));
            //textMessage.acknowledge();

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
