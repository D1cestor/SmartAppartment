package eu.tsp.smartappartment.room.controller.service;

public interface WindowControlService
{

    Boolean getWindow(int uid);
    void setWindow(int uid, Boolean state);
}