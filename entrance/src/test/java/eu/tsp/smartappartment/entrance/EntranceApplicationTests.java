package eu.tsp.smartappartment.entrance;

import eu.tsp.smartappartment.entrance.callback.OnMessageCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class EntranceApplicationTests
{

    String broker = "tcp://127.0.0.1:1883";
    @Test
    void contextLoads()
    {
        String subTopic = "testtopic/#";
        String pubTopic = "testtopic/1";
        String content = "Hello World";

        try {
            MqttClient client2 = new MqttClient(broker, "sub");
            client2.setCallback(new OnMessageCallback());
            client2.connect();
            client2.subscribe(subTopic);
            MqttClient client1 = new MqttClient(broker, "pub");
            client1.connect();
            MqttMessage message = new MqttMessage();
            message.setPayload(content.getBytes());
            client1.publish(pubTopic, message);
            client1.disconnect();
            client2.disconnect();
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }


    @Test
    void deliveryTest() throws MqttException
    {
//        MqttClient client = new MqttClient(broker, "test");
//        client.setCallback(new OnMessageCallback());
//        client.connect();
//        client.subscribe("delivery/401");
//        while (true)
//        {
//
//        }
    }

}
