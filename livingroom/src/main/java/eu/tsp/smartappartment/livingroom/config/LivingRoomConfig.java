package eu.tsp.smartappartment.livingroom.config;


import eu.tsp.smartappartment.livingroom.entity.PersonEntity;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
@Configuration
@ConfigurationProperties(prefix = "room-config")
public class LivingRoomConfig
{
    int id;
    ArrayList<PersonEntity> tenants;
    int temperature;
}
