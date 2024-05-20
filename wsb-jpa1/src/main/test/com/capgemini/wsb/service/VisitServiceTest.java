package com.capgemini.wsb.service;

import com.capgemini.wsb.persistence.dao.VisitDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;
    @Autowired
    private VisitDao visitDao;

    @Test
    public void testShouldReturnVisitsByPatientId() {
        final long patientId = 1L;
        var visits = visitService.findByPatientId(patientId);
        for (var visit : visits) {
            var visitEntity = visitDao.findOne(visit.getId());
            assertThat(visit.getPatient().getId()).isEqualTo(patientId);
            assertThat(visit.getPatient().getId()).isEqualTo(visitEntity.getPatient().getId());
        }
    }

}
