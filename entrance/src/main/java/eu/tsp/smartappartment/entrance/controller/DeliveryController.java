package eu.tsp.smartappartment.entrance.controller;


import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("entrance/delivery")
public class DeliveryController
{

    private MqttClient client;

    @Autowired
    public DeliveryController(MqttClient client)
    {
        this.client = client;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void newPackage(@RequestBody int room)
    {
        String content = "New package";
        String topic = "delivery/" + room;
        try
        {
            client.connect();
            MqttMessage message = new MqttMessage();
            message.setRetained(true);
            message.setPayload(content.getBytes());
            client.publish(topic, message);
            client.disconnect();
        } catch (MqttException e)
        {
            throw new RuntimeException(e);
        }


    }
}
