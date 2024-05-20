package com.capgemini.wsb.persistance.dao;

import com.capgemini.wsb.persistence.dao.PatientDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Transactional
    @Test
    public void testShouldFindPatientsByLastName() {
        final String lastName = "Johnson";
        var patients = patientDao.findByLastName(lastName);
        for (var patient : patients) {
            assertThat(patient).isNotNull();
            assertThat(patient.getLastName()).isEqualTo(lastName);
        }
    }

    @Transactional
    @Test
    public void testShouldReturnPatientsWhoHaveMoreVisitsThan() {
        final long visitsCount = 1L;
        var patients = patientDao.findPatientsHavingMoreThanXVisits(visitsCount);
        for (var patient : patients) {
            assertThat(patientDao.findOne(patient.getId()).getVisits().stream().count()).isGreaterThan(visitsCount);
        }
    }

    @Transactional
    @Test
    public void testShouldReturnPatientsWhoseWeightIsGreaterThan() {
        final int weight = 90;
        var patients = patientDao.findPatientsHavingWeightGreaterThan(weight);
        for (var patient : patients) {
            assertThat(patientDao.findOne(patient.getId()).getWeight()).isGreaterThan(weight);
        }
    }
}
