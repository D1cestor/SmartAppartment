package eu.tsp.smartappartment.livingroom.entity;

import lombok.Data;
import java.util.ArrayList;

@Data
public class TemperatureRecord
{
    ArrayList<PersonalTemperaturePreference> records;
    int duration = 2;
}
