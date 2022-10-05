package eu.tsp.smartappartment.room.controller.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoomEntity implements Serializable
{
    private String name;

    private int roomId;

    private Boolean light;
    private Boolean window;
    private int temperature;

}
