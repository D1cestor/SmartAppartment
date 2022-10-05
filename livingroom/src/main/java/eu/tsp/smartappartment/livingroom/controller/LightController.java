package eu.tsp.smartappartment.livingroom.controller;

import eu.tsp.smartappartment.livingroom.service.LightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/light")
public class LightController
{
    private LightService lightService;

    @Autowired
    public LightController(LightService lightService)
    {
        this.lightService = lightService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public boolean getLight()
    {
        return lightService.getLight();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void setLight(@RequestBody Boolean state)
    {
        lightService.setLight(state);
    }

}
