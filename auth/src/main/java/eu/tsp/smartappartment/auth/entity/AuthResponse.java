package eu.tsp.smartappartment.auth.entity;


import lombok.Data;

@Data
public class AuthResponse
{
    private String role;
    private int roomId;
    private int personId;
    private String room;
    private String livingRoom;
    private String kitchen;
    private String name;
}
