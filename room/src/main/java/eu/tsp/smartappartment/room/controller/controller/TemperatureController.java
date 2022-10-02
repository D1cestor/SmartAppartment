package eu.tsp.smartappartment.room.controller.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("room/temperature")
public class TemperatureController
{
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
}
