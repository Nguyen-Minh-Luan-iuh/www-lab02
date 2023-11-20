package com.example.www_lab_w2.convert;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.joda.time.DateTime;

import java.util.Date;

@Converter(autoApply = true)
public class DateTimeCV implements AttributeConverter<DateTime, Date> {
    @Override
    public Date convertToDatabaseColumn(DateTime dateTime) {
        if(dateTime != null){
            return dateTime.toDate();
        }
        return null;
    }

    @Override
    public DateTime convertToEntityAttribute(Date date) {
        if(date != null){
            return new DateTime(date);
        }
        return null;
    }
}