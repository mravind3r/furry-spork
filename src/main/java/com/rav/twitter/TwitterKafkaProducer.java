package com.rav.twitter;

import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

public class TwitterKafkaProducer {


	private String topic;
	private KafkaProducer<String,String> kafkaProducer;
	private BlockingQueue<String> queue;
	private Client client;


	public TwitterKafkaProducer(String topic,String consumerKey, String consumerSecret,
					String token, String secret) {
		this.topic = topic;
		Properties config = new Properties();
		config.put("bootstrap.servers", "localhost:9092");
		config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		config.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,TwitterPartition.class.getName());
		config.put("numpartitions","3"); // useful for custom partition
		kafkaProducer = new KafkaProducer<String, String>(config);

		// twitter related
		queue = new LinkedBlockingQueue<String>(100);
		StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
		// add some track terms
		endpoint.trackTerms(Lists.newArrayList(
						"#GeneralElection"));
		Authentication auth = new OAuth1(consumerKey, consumerSecret, token,
						secret);
		// Create a new BasicClient. By default gzip is enabled.
		 client = new ClientBuilder().hosts(Constants.STREAM_HOST)
						.endpoint(endpoint).authentication(auth)
						.processor(new StringDelimitedProcessor(queue)).build();

		// Establish a connection
		client.connect();
	}


	public void sendQueuedMessages(){
		try {
			for(int i=0;i<1000;i++) {
				ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(this.topic,String.valueOf(i), queue.take());
				kafkaProducer.send(producerRecord, new Callback() {
					public void onCompletion(RecordMetadata recordMetadata, Exception e) {
						System.out.println(" topic:" + recordMetadata.topic() + "  partition:" + recordMetadata.partition());
					}
				});
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			kafkaProducer.close();
			client.stop();
		}
	}
}