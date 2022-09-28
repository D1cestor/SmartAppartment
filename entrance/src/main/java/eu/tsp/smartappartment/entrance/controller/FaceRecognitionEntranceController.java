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
@RequestMapping("entrance/entranceCamera")
public class FaceRecognitionEntranceController
{
    private FindFaceService findFaceService;
    private MqttClient client;

    @Autowired
    public FaceRecognitionEntranceController(FindFaceService findFaceService, MqttClient client)
    {
        this.findFaceService = findFaceService;
        this.client = client;
    }
    @RequestMapping(value = "/face", method = RequestMethod.POST)
    public boolean faceRecognition(@RequestBody String face)
    {
        return (findFaceService.findFace(face));
    }
}
