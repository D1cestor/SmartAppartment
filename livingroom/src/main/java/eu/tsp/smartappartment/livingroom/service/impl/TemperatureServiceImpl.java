package eu.tsp.smartappartment.livingroom.service.impl;

import eu.tsp.smartappartment.livingroom.config.LivingRoomConfig;
import eu.tsp.smartappartment.livingroom.entity.PersonalTemperaturePreference;
import eu.tsp.smartappartment.livingroom.entity.TemperatureRecord;
import eu.tsp.smartappartment.livingroom.service.TemperatureService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Slf4j
@Service("SetTemperatureService")
public class TemperatureServiceImpl implements TemperatureService
{
    private LivingRoomConfig config;

    @Autowired
    public TemperatureServiceImpl(LivingRoomConfig config)
    {
        this.config = config;
    }


    @Override
    public void SetTemperature(PersonalTemperaturePreference preference)
    {
        TemperatureRecord records = config.getRecord();
        int duration = records.getDuration();
        Date currentDate = new Date();
        int temperature = preference.getTemperature();
        for (var record: records.getRecords())
        {
            Date recordDate = record.getDate();
            // if the record expired, remove it
            if (currentDate.compareTo(recordDate) > 0)
                records.getRecords().remove(record);
            else
                temperature += record.getTemperature();
        }
        Date recordDate = preference.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(recordDate);
        calendar.add(Calendar.HOUR, duration);
        recordDate = calendar.getTime();
        preference.setDate(recordDate);
        records.getRecords().add(preference);
        // average all the valid temperature
        log.info("Temperature is now " + temperature / records.getRecords().size());
        config.setTemperature(temperature / records.getRecords().size());
    }

    @Override
    public int getTemperature()
    {
        return config.getTemperature();
    }


}
