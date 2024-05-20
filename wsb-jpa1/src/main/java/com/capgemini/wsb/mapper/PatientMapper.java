package com.capgemini.wsb.mapper;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.persistence.entity.PatientEntity;

import java.util.stream.Collectors;

public final class PatientMapper {

    public static PatientTO mapToTO(final PatientEntity patientEntity) {
        if (patientEntity == null) {
            return null;
        }
        final PatientTO patientTO = new PatientTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setAddress(AddressMapper.mapToTO(patientEntity.getAddress()));
        patientTO.setVisits(patientEntity.getVisits().stream().map(VisitMapper::mapToTO).collect(Collectors.toList()));
        patientTO.setWeight(patientEntity.getWeight());
        return patientTO;
    }
}
