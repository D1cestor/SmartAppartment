package eu.tsp.smartappartment.room.controller.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("room/window")
public class WindowController
{
    public Boolean getWindow()
    {
        return true;
    }
}
