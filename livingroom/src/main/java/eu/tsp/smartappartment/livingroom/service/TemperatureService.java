package eu.tsp.smartappartment.livingroom.service;

import eu.tsp.smartappartment.livingroom.entity.PersonalTemperaturePreference;

public interface TemperatureService
{
    void SetTemperature(PersonalTemperaturePreference preference);

    int getTemperature();

    Boolean getAc();

    void setAc(Boolean state);
}
