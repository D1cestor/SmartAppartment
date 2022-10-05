package eu.tsp.smartappartment.room.controller.controller;

import eu.tsp.smartappartment.room.controller.service.LightControlService;
import eu.tsp.smartappartment.room.controller.service.WindowControlService;
import eu.tsp.smartappartment.room.controller.service.TemperatureControlService;
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
    private LightControlService lightControlService;
    private WindowControlService windowControlService;
    private TemperatureControlService temperatureControlService;
    private MqttClient client;

    @Autowired
    public SpeakerController(SpeechRecognitionService speechRecognitionService,LightControlService lightControlService, WindowControlService windowControlService, TemperatureControlService temperatureControlService, MqttClient client)
    {
        this.speechRecognitionService = speechRecognitionService;
        this.lightControlService = lightControlService;
        this.windowControlService = windowControlService;
        this.temperatureControlService = temperatureControlService;
        this.client = client;
    }
    @RequestMapping(value = "/speech", method = RequestMethod.POST)
    public int speechRecognitionService(int uid, int speech)
    {
        int cmd = speechRecognitionService.findCmd(speech);
        String url = "";
        String opt = "";

        switch(cmd) {
            case 0:
                lightControlService.setLight(uid,true);
                break;
            case 1:
                lightControlService.setLight(uid,false);
                break;
            case 2:
                windowControlService.setWindow(uid,true);
                break;
            case 3:
                windowControlService.setWindow(uid,false);
                break;

        }
        //lightControlService.SendCmd(url, opt);
        //To do

        return cmd;
    }
}
