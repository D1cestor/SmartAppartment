package eu.tsp.smartappartment.auth.controller;


import eu.tsp.smartappartment.auth.config.AuthConfig;
import eu.tsp.smartappartment.auth.entity.AuthResponse;
import eu.tsp.smartappartment.auth.entity.PersonEntity;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController
{
    private AuthConfig config;

    @Autowired
    public AuthController(AuthConfig config)
    {
        this.config = config;
    }


    @RequestMapping(method = RequestMethod.POST)
    public AuthResponse auth(@RequestBody PersonEntity person)
    {
        var users = config.getUsers();
        for (var user: users)
        {
            if (user.getName().equals(person.getName()) && user.getPassword().equals(person.getPassword()))
            {
                AuthResponse authResponse = new AuthResponse();
                authResponse.setRole(user.getRole().toString());
                authResponse.setKitchen(user.getKitchen());
                authResponse.setLivingRoom(user.getLivingRoom());
                authResponse.setRoomId(user.getRoomId());
                authResponse.setRoom(user.getRoom());
                authResponse.setPersonId(user.getPersonId());
                authResponse.setName(user.getName());
                return authResponse;
            }
        }
        return null;
    }
}
