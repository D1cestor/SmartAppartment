package eu.tsp.smartappartment.room.controller.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("room/light")
public class LightController
{
    /*public Boolean getLight()
    {
        return true;
    }*/

    @RequestMapping(value = "/switch", method = RequestMethod.POST)
    public int lightControl(@RequestBody String lightCtrl) {
        String lightOpen = "open";
        String lightClose = "close";

        if(lightCtrl.equals(lightOpen)) {
            return 1;
        }
        else if(lightCtrl.equals(lightClose)) {
            return 0;
        }
        else {
            return -1;    //do nothing
        }
    }
}
