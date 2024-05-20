package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.VisitTO;

import java.util.List;

public interface VisitService {
    public List<VisitTO> findByPatientId(final Long patientId);

}
