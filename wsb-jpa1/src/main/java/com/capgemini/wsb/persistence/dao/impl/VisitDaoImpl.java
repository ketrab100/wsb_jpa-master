package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.VisitDao;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VisitDaoImpl extends AbstractDao<VisitEntity, Long> implements VisitDao {

    @Override
    public List<VisitEntity> findByPatientId(Long patientId) {
        return entityManager.createQuery("select visit from VisitEntity visit " +
                        "join visit.patient patient" +
                        " where patient.id = :param1", VisitEntity.class)
                .setParameter("param1", patientId)
                .getResultList();
    }
}
