package eu.tsp.smartappartment.livingroom.controller;

import eu.tsp.smartappartment.livingroom.service.WindowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/window")
public class WindowController
{
    private WindowService windowService;

    @Autowired
    public WindowController(WindowService windowService)
    {
        this.windowService = windowService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public boolean getWindow()
    {
        return this.windowService.getWindow();
    }

    @RequestMapping(method = RequestMethod.POST)
    public boolean setWindow(@RequestBody String state)
    {
        this.windowService.setWindow(state.equals("true"));
        return true;
    }
}
