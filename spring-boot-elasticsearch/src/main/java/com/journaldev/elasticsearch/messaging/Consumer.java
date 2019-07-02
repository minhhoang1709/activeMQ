package com.journaldev.elasticsearch.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.journaldev.elasticsearch.dao.UserDao;

@Component
public class Consumer implements MessageListener {
	@Autowired
	UserDao userDao;

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		try {
			String messageData = "";
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				messageData = textMessage.getText();
			}
			System.out.print("Receive\n:" + messageData);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}