package eu.tsp.smartappartment.livingroom.service.impl;

import eu.tsp.smartappartment.livingroom.config.LivingRoomConfig;
import eu.tsp.smartappartment.livingroom.service.WindowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("WindowService")
public class WindowServiceImpl implements WindowService
{
    private LivingRoomConfig config;

    @Autowired
    public WindowServiceImpl(LivingRoomConfig config)
    {
        this.config = config;
    }

    @Override
    public boolean getWindow()
    {
        return config.getWindow();
    }

    @Override
    public void setWindow(Boolean state)
    {
        config.setWindow(state);
    }
}
