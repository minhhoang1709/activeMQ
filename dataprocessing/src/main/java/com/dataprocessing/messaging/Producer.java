package com.dataprocessing.messaging;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import java.net.URISyntaxException;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jms.core.MessageCreator;
import javax.jms.ObjectMessage;

import com.dataprocessing.model.UserModel;

@Component
public class Producer {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	public void sendMessagev2(final String userName, String queuename) {
		jmsTemplate.setDefaultDestinationName(queuename);
		jmsTemplate.send(new MessageCreator(){
				@Override
				public Message createMessage(Session session) throws JMSException{
					TextMessage objectMessage = session.createTextMessage(userName);
					return objectMessage;
				}
			});
	}
}
