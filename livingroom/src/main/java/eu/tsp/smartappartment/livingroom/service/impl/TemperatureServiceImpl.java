package eu.tsp.smartappartment.livingroom.service.impl;

import eu.tsp.smartappartment.livingroom.config.LivingRoomConfig;
import eu.tsp.smartappartment.livingroom.entity.PersonalTemperaturePreference;
import eu.tsp.smartappartment.livingroom.entity.TemperatureRecord;
import eu.tsp.smartappartment.livingroom.service.TemperatureService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        preference.setDate(new Date());
        int duration = 2;
        TemperatureRecord records = config.getRecord();
        int temperature = preference.getTemperature();
        if (config.getRecord() == null)
        {
            config.setRecord(new TemperatureRecord());
            config.getRecord().setRecords(new ArrayList<>());
            records = config.getRecord();
        }
        else
        {
            Date currentDate = new Date();
            for (var record: records.getRecords())
            {
                Date recordDate = record.getDate();
                // if the record expired, remove it
                if (currentDate.compareTo(recordDate) > 0 || record.getPerson().getPersonId() == preference.getPerson().getPersonId())
                    records.getRecords().remove(record);
                else
                    temperature += record.getTemperature();
                if (records.getRecords().size() == 0)
                    break;
            }
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

    @Override
    public Boolean getAc()
    {
        return config.getAc();
    }

    @Override
    public void setAc(Boolean state)
    {
        config.setAc(state);
        log.info("AC is now "+ (state? "on": "off"));
    }


}
