package eu.tsp.smartappartment.room.controller.service.impl;


import eu.tsp.smartappartment.room.controller.service.SpeechRecognitionService;
import org.springframework.stereotype.Service;


@Service("SpeechRecognitionService")
public class SpeechRecognitionServiceImpl implements SpeechRecognitionService
{
    @Override
    public int findCmd(String speech)
    {
        return 1;
    }
}
