package eu.tsp.smartappartment.room.controller.config;

import eu.tsp.smartappartment.room.controller.entity.RoomEntity;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "room-config")
public class RoomConfig
{
    int roomNumber;
    ArrayList<RoomEntity> rooms = new ArrayList<RoomEntity>();

    RoomConfig(){
        roomNumber = 4;
        for(int i = 0; i < roomNumber; i++){
            rooms.add(new RoomEntity());
            rooms.get(i).setRoomId(i);
            rooms.get(i).setLight(false);
            rooms.get(i).setWindow(false);
            rooms.get(i).setTemperature(25);
        }
    }

    public boolean getLight(int id){
        return rooms.get(id).getLight();
    }

    public void setLight(int id, boolean state){
        rooms.get(id).setLight(state);
    }

    public boolean getWindow(int id){
        return rooms.get(id).getWindow();
    }

    public void setWindow(int id, boolean state){
        rooms.get(id).setWindow(state);
    }

    public int getTemperature(int id){
        return rooms.get(id).getTemperature();
    }

    public void setTemperature(int id, int temp){
        rooms.get(id).setTemperature(temp);
    }

}
