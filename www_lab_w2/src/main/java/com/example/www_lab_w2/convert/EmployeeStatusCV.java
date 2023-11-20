package com.example.www_lab_w2.convert;

import com.example.www_lab_w2.enums.EmployeeStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class EmployeeStatusCV implements AttributeConverter<EmployeeStatus,Integer> {
    @Override
    public Integer convertToDatabaseColumn(EmployeeStatus employeeStatus) {
        if(employeeStatus != null){
            return employeeStatus.getValue();
        }

        return null;
    }

    @Override
    public EmployeeStatus convertToEntityAttribute(Integer integer) {
        if(integer == null){
            return null;
        }
        return Stream.of(EmployeeStatus.values()).filter(c -> c.getValue() == integer).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}