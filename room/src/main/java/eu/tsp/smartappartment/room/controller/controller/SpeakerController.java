package eu.tsp.smartappartment.room.controller.controller;

import eu.tsp.smartappartment.room.controller.service.SpeechRecognitionService;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("room/speaker")
public class SpeakerController
{
    private SpeechRecognitionService speechRecognitionService;
    private MqttClient client;

    @Autowired
    public SpeakerController(SpeechRecognitionService speechRecognitionService, MqttClient client)
    {
        this.speechRecognitionService = speechRecognitionService;
        this.client = client;
    }
    @RequestMapping(value = "/speech", method = RequestMethod.POST)
    public int speechRecognitionService(@RequestBody String speech)
    {
        int cmd = speechRecognitionService.findCmd(speech);

        /*switch(cmd) {
            case 0:

        }*/

        //To do

        return cmd;
    }
}
