package eu.tsp.smartappartment.entrance.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonEntity implements Serializable
{
    private int personId;

    private String name;

    private int roomId;

    private String face;

}
