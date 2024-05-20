package com.capgemini.wsb.mapper;

import com.capgemini.wsb.dto.PatientSimpleTO;
import com.capgemini.wsb.persistence.entity.PatientEntity;

public class PatientSimpleMapper {
    public static PatientSimpleTO mapToTO(final PatientEntity patientEntity) {
        if (patientEntity == null) {
            return null;
        }
        final PatientSimpleTO patientTO = new PatientSimpleTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setAddress(AddressMapper.mapToTO(patientEntity.getAddress()));
        return patientTO;
    }
}
