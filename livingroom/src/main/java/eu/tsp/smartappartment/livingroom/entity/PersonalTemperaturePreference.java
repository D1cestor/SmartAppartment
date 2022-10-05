package eu.tsp.smartappartment.livingroom.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PersonalTemperaturePreference
{
    PersonEntity person;
    int temperature;
    Date date;
}
