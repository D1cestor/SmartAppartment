package eu.tsp.smartappartment.entrance.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/camera")
public class CameraController
{
    @RequestMapping(value = "/face", method = RequestMethod.POST)
    public String faceRecognition(@RequestBody String face)
    {
        String ans = "123123123";
        String response = "";
        if (ans.equals(face))
            response += "Welcome home";
        else
            response += "Get out";
        return response;
    }
}
