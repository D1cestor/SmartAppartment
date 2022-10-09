package eu.tsp.smartappartment.auth.config;


import eu.tsp.smartappartment.auth.entity.PersonEntity;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "auth-config")
public class AuthConfig
{
    ArrayList<PersonEntity> users;
}
