package eu.tsp.smartappartment.room.controller.service.impl;

import eu.tsp.smartappartment.room.controller.config.RoomConfig;
import eu.tsp.smartappartment.room.controller.service.WindowControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("WindowControlService")
public class WindowControlServiceImpl implements WindowControlService {

    private RoomConfig config;

    @Autowired
    public WindowControlServiceImpl(RoomConfig config)
    {
        this.config = config;
    }


    @Override
    public Boolean getWindow(int uid)
    {
        return config.getWindow(uid);
    }

    @Override
    public void setWindow(int uid, Boolean state)
    {
        config.setWindow(uid, state);
        for(int i = 0; i < 4; i++) {
            log.info("Window in room " + i + " is " + (config.getWindow(i) ? "on" : "off"));
            log.info("Light in room " + i + " is " + (config.getLight(i) ? "on" : "off"));
        }
    }

}

