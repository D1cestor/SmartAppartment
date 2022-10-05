package eu.tsp.smartappartment.room.controller.service.impl;

import eu.tsp.smartappartment.room.controller.config.RoomConfig;
import eu.tsp.smartappartment.room.controller.service.TemperatureControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("TemperatureControlService")
public class TemperatureControlServiceImpl implements TemperatureControlService {

    private RoomConfig config;

    @Autowired
    public TemperatureControlServiceImpl(RoomConfig config)
    {
        this.config = config;
    }


    @Override
    public int getTemperature(int uid)
    {
        return config.getTemperature(uid);
    }

    @Override
    public void setTemperature(int uid, int state)
    {
        config.setTemperature(uid, state);
        for(int i = 0; i < 4; i++) {
            log.info("Light in room " + i + " is " + config.getTemperature(i));
        }
    }

}
