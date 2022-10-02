package eu.tsp.smartappartment.livingroom.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class TemperatureRecord
{
    ArrayList<PersonalTemperaturePreference> records;
    Date date;
}
