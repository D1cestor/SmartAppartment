package eu.tsp.smartappartment.entrance.service.impl;

import eu.tsp.smartappartment.entrance.service.FindFaceService;
import org.springframework.stereotype.Service;


@Service("FindFaceService")
public class FindFaceServiceImpl implements FindFaceService
{
    @Override
    public boolean findFace(String face)
    {
        return true;
    }
}
