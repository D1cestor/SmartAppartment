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
    }
}
