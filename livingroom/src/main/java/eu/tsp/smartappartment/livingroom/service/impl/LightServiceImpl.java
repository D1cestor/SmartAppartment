package eu.tsp.smartappartment.livingroom.service.impl;

import eu.tsp.smartappartment.livingroom.config.LivingRoomConfig;
import eu.tsp.smartappartment.livingroom.service.LightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("LightService")
public class LightServiceImpl implements LightService
{
    private LivingRoomConfig config;

    @Autowired
    public LightServiceImpl(LivingRoomConfig config)
    {
        this.config = config;
    }

    @Override
    public Boolean getLight()
    {
        return config.getLight();
    }

    @Override
    public void setLight(Boolean state)
    {
        config.setLight(state);
        log.info("Light is" + (state? "on": "off"));
    }
}
