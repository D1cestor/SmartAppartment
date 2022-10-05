package eu.tsp.smartappartment.livingroom.controller;

import eu.tsp.smartappartment.livingroom.entity.PersonalTemperaturePreference;
import eu.tsp.smartappartment.livingroom.service.TemperatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/temperature")
public class TemperatureController
{

    private TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService temperatureService)
    {
        this.temperatureService = temperatureService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Boolean setTemperature(@RequestBody PersonalTemperaturePreference preference)
    {
        temperatureService.SetTemperature(preference);
        return true;
    }

    @RequestMapping(method = RequestMethod.GET)
    public int getTemperature()
    {
        return temperatureService.getTemperature();
    }
}
