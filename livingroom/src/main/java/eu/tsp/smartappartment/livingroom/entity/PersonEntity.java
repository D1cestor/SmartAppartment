package eu.tsp.smartappartment.livingroom.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonEntity implements Serializable
{
    public enum Role {student, guest, safety_guard, cleaner}

    private int personId;

    private String name;

    private int roomId;

    private String face;

    private Role role;
}
