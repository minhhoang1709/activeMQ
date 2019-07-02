package com.journaldev.elasticsearch.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import java.util.Map;
import com.google.gson.Gson;

@Component
public class Producer {
	@Autowired
	JmsTemplate jmsTemplate;
	
	public void sendMessagev2(final String userName) {

		jmsTemplate.send(new MessageCreator(){
				@Override
				public Message createMessage(Session session) throws JMSException{
					TextMessage objectMessage = session.createTextMessage(userName);
					return objectMessage;
				}
			});
	}
}
