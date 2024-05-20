package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.persistence.dao.DoctorDao;
import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.dao.VisitDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {
    @Autowired
    private PatientService patientService;
    @Autowired
    private VisitDao visitDao;
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private PatientDao patientDao;

    @Test
    public void testShouldRemovePatientWithVisitsCascade() {
        final Long patientId = 2L;
        var doctors = patientService.findById(patientId).getVisits().stream().map(VisitTO::getDoctor).collect(Collectors.toList());
        patientService.delete(patientId);
        var visits = visitDao.findAll().stream().filter(x -> x.getPatient().getId().equals(patientId));
        assertThat(visits.count()).isEqualTo(0);
        doctors.forEach(x -> {
            assertThat(doctorDao.findOne(x.getId())).isNotNull();
        });
    }

    @Test
    public void testShouldReturnPatientTO() {
        final Long patientId = 1L;
        var patient = patientService.findById(patientId);
        assertThat(patient).isNotNull();

        var patientEntity = patientDao.findOne(patientId);
        assertThat(patient.getWeight()).isEqualTo(patientEntity.getWeight());
        assertThat(patient.getPatientNumber()).isEqualTo(patientEntity.getPatientNumber());
    }
}
