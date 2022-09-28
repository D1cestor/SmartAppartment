package eu.tsp.smartappartment.entrance.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/delivery")
public class DeliveryController
{
    @RequestMapping(method = RequestMethod.POST)
    public void newPackage(@RequestBody int room)
    {

    }
}
