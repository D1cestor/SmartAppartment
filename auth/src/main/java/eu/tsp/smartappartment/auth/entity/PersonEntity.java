package eu.tsp.smartappartment.auth.entity;

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

    private String password;

    private String kitchen;

    private String livingRoom;

    private String room;


}
