package eu.tsp.smartappartment.livingroom.controller;

import eu.tsp.smartappartment.livingroom.entity.PersonalTemperaturePreference;
import eu.tsp.smartappartment.livingroom.service.TemperatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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

    @RequestMapping(method = RequestMethod.GET)
    public Boolean getAc()
    {
        return temperatureService.getAc();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void setAc(@RequestBody String state)
    {
        temperatureService.setAc(state.equals("true"));
    }

    @RequestMapping(value = "/number", method = RequestMethod.GET)
    public int getTemperature()
    {
        return temperatureService.getTemperature();
    }

    @RequestMapping(value = "/number", method = RequestMethod.POST)
    public void setTemperature(@RequestBody PersonalTemperaturePreference preference)
    {
        temperatureService.SetTemperature(preference);
        log.info("Temperature is now " + temperatureService.getTemperature());
    }
}
