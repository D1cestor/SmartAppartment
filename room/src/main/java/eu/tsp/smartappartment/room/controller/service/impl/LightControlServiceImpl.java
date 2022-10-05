package eu.tsp.smartappartment.room.controller.service.impl;

import eu.tsp.smartappartment.room.controller.service.LightControlService;
import eu.tsp.smartappartment.room.controller.config.RoomConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service("LightControlService")
public class LightControlServiceImpl implements LightControlService {

    private RoomConfig config;

    @Autowired
    public LightControlServiceImpl(RoomConfig config)
    {
        this.config = config;
    }


    @Override
    public Boolean getLight(int uid)
    {
        return config.getLight(uid);
    }

    @Override
    public void setLight(int uid, Boolean state)
    {
        config.setLight(uid, state);
        for(int i = 0; i < 4; i++) {
            log.info("Light in room " + i + " is " + (config.getLight(i) ? "on" : "off"));
        }
    }

}
