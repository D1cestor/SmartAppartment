package eu.tsp.smartappartment.room.controller.service;

public interface TemperatureControlService
{

    int getTemperature(int uid);
    void setTemperature(int uid, int temp);
}