package eu.tsp.smartappartment.room.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("room/light")
public class LightController
{
    public Boolean getLight()
    {
        return true;
    }
}
