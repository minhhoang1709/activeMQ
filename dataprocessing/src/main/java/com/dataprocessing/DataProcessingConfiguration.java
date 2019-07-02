package com.dataprocessing;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import com.dataprocessing.messaging.CrawingConsumer;
import com.dataprocessing.messaging.EsConsumer;

@Configuration
public class DataProcessingConfiguration {
	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;

	@Value("${spring.activemq.broker-username}")
	private String brokerUsername;

	@Value("${spring.activemq.broker-password}")
	private String brokerPassword;
	
	@Autowired
	CrawingConsumer crawingconsumer;
	
	@Autowired
	EsConsumer esConsumer;

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(brokerUrl);
		connectionFactory.setUserName(brokerUsername);
		connectionFactory.setPassword(brokerPassword);

		return connectionFactory;
	}
	@Bean
	public ConnectionFactory cachingConnectionFactory(){
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setTargetConnectionFactory(connectionFactory());
		connectionFactory.setSessionCacheSize(10);
		return connectionFactory;
	}
	
	@Bean
	public MessageListenerContainer getCrawingContainer(){
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.setDestinationName("crawingresult.queue");
		container.setMessageListener(crawingconsumer);
		return container;
	}
	
	@Bean
	public MessageListenerContainer getEsContainer(){
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.setDestinationName("esresult.queue");
		container.setMessageListener(crawingconsumer);
		return container;
	}
	
	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestinationName("crawing.queue");
		return template;
	}
	
	@Bean 
	MessageConverter converter(){
		return new SimpleMessageConverter();
	}

//	@Bean
//	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
//		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//		factory.setConnectionFactory(connectionFactory());
//		factory.setConcurrency("3-10");
//		// true: using jms topic, false: using jms queue
//		factory.setPubSubDomain(false);
//
//		return factory;
//	}
}
