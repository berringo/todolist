package berringo.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Sender extends Thread{

  private static final Logger LOGGER =
      LoggerFactory.getLogger(Sender.class);


  private ObjectMapper mapper= new ObjectMapper();
  
  @Autowired
  private KafkaTemplate<String, String> template;

  public void send(Object payload, String action) {
    LOGGER.info("sending payload='{}'", payload);
    try {
    	String todoString = mapper.writeValueAsString(payload);
		this.template.send("part-demo",action, todoString);
	} catch (Exception e) {
		LOGGER.error("we have a problem:", e.getMessage());
		e.printStackTrace();
	}
  }
  
	@Bean
	public ProducerFactory<String, Object> producerFactory() {
	    return new DefaultKafkaProducerFactory<String, Object>(producerConfigs());
	}

	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();
	    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
	    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
	    // See https://kafka.apache.org/documentation/#producerconfigs for more properties
	    return props;
	}

	@Bean
	public KafkaTemplate<?, ?> kafkaTemplate() {
	    return new KafkaTemplate<String, Object> (producerFactory());
	}
}