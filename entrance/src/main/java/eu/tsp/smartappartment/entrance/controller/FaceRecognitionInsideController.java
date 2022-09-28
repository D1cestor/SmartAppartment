package eu.tsp.smartappartment.entrance.controller;

import eu.tsp.smartappartment.entrance.service.FindFaceService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("entrance/InsideCamera")
public class FaceRecognitionInsideController
{
    private FindFaceService findFaceService;
    private MqttClient client;


    @Autowired
    public FaceRecognitionInsideController(FindFaceService findFaceService, MqttClient client)
    {
        this.findFaceService = findFaceService;
        this.client = client;
    }
    @RequestMapping(method = RequestMethod.POST)
    public void faceRecognition(@RequestBody String face)
    {
        if(!findFaceService.findFace(face))
        {
            try
            {
                String content = "A sus person appears";
                String topic = "invade/";
                client.connect();
                MqttMessage message = new MqttMessage();
                message.setPayload(content.getBytes());
                client.publish(topic, message);
                client.disconnect();
            } catch (MqttException e)
            {
                throw new RuntimeException(e);
            }

        }
    }
}
