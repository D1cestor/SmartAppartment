package eu.tsp.smartappartment.room.controller.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("room/window")
public class WindowController
{
    @RequestMapping(value = "/switch", method = RequestMethod.POST)
    public int windowControl(@RequestBody String windowStr) {
        String str1 = "open";
        String str2 = "close";

        if(windowStr.equals(str1)) {
            return 1;
        }
        else if(windowStr.equals(str2)) {
            return 0;
        }
        else {
            return -1;    //do nothing
        }
    }
}
