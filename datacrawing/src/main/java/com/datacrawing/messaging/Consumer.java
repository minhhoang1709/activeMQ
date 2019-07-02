package com.datacrawing.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.datacrawing.service.UserService;

@Component
public class Consumer implements MessageListener {
	@Autowired
	MessageConverter messageConverter;

	@Autowired
	UserService userService;

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		try {
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				String userName = textMessage.getText();
				userService.processUser(userName);
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
