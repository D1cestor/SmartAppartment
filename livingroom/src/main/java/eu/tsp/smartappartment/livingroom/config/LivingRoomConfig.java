package eu.tsp.smartappartment.livingroom.config;


import eu.tsp.smartappartment.livingroom.entity.PersonEntity;
import eu.tsp.smartappartment.livingroom.entity.PersonalTemperaturePreference;
import eu.tsp.smartappartment.livingroom.entity.TemperatureRecord;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "room-config")
public class LivingRoomConfig
{
    int id;
    Boolean light;
    ArrayList<PersonEntity> tenants;
    int temperature;
    TemperatureRecord record;
}
