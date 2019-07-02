package com.dataprocessing.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.Destination;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.dataprocessing.model.UserModel;
import com.dataprocessing.servicce.CrawingService;
import com.dataprocessing.servicce.SearchingService;

@Component
public class CrawingConsumer implements MessageListener {

	@Autowired
	CrawingService crawingService;
	
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		try {
			String messageData = "";
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				messageData = textMessage.getText();
			}
			if(messageData!=null) {
				List<UserModel> users = new ArrayList<UserModel>();
				users = crawingService.convertStringToUserList(messageData);
				crawingService.insertUserList(users);
			}else {
				System.out.println("Failed");
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
