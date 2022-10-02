package eu.tsp.smartappartment.livingroom.controller;

import eu.tsp.smartappartment.livingroom.entity.PersonalTemperaturePreference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/temperature")
public class TemperatureController
{
    @RequestMapping(method = RequestMethod.POST)
    public Boolean setTemperature(@RequestBody PersonalTemperaturePreference preference)
    {

        return true;
    }
}
