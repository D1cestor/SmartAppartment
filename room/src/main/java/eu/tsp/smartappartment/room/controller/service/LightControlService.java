package eu.tsp.smartappartment.room.controller.service;

public interface LightControlService
{

    Boolean getLight(int uid);
    void setLight(int uid, Boolean state);
}
