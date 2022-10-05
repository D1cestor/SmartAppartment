package eu.tsp.smartappartment.room.controller.controller;

import eu.tsp.smartappartment.room.controller.service.LightControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("room/light")
public class LightController
{

    private LightControlService lightControlService;

    @Autowired
    public LightController(LightControlService lightControlService)
    {
        this.lightControlService = lightControlService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public boolean getLight(int uid)
    {
        return lightControlService.getLight(uid);
    }

    @RequestMapping(method = RequestMethod.POST)
    public boolean setLight(int uid,  Boolean state)
    {
        lightControlService.setLight(uid,state);
        return state;
    }

    /*@RequestMapping(value = "/switch")
    public int lightControl(@RequestBody String lightStr) {
        String str1 = "open";
        String str2 = "close";

        if(lightStr.equals(str1)) {
            return 1;
        }
        else if(lightStr.equals(str2)) {
            return 0;
        }
        else {
            return -1;    //do nothing
        }
    }*/
}
