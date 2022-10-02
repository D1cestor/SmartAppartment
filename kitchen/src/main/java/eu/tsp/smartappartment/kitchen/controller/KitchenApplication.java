package eu.tsp.smartappartment.kitchen.controller;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KitchenApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(KitchenApplication.class, args);
    }

    String broker = "tcp://127.0.0.1:1883";
    @Bean
    public MqttClient deliveryClient()
    {
        try
        {
            MqttClient client = new MqttClient(broker, "sys");
            return client;
        } catch (MqttException e)
        {
            throw new RuntimeException(e);
        }
    }
}
