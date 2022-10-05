package eu.tsp.smartappartment.room.controller.controller;

import eu.tsp.smartappartment.room.controller.config.RoomConfig;
import eu.tsp.smartappartment.room.controller.service.LightControlService;
import eu.tsp.smartappartment.room.controller.service.TemperatureControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("room/temperature")
public class TemperatureController
{
    private TemperatureControlService temperatureControlService;

    @Autowired
    public TemperatureController(TemperatureControlService temperatureControlService)
    {
        this.temperatureControlService = temperatureControlService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public int getTemperature(int uid)
    {
        return temperatureControlService.getTemperature(uid);
    }

    @RequestMapping(method = RequestMethod.POST)
    public int setTemperature(int uid,  int state)
    {
        temperatureControlService.setTemperature(uid,state);
        return state;
    }

    /*
    @RequestMapping(value = "/number", method = RequestMethod.POST)
    public int temperatureControl(@RequestBody String temperatureStr) {
        int minT = 18;
        int maxT = 32;

        try{
            int tmp = Integer.parseInt(temperatureStr);

            if(tmp >= minT && tmp <= maxT) {
                return tmp;
            }
            else {
                return -1;    //do nothing
            }
        }
        catch (Exception e){    //NaN
            return -1;
        }


    }

     */
}
