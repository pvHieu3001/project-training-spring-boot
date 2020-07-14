package com.smartosc.training.services.impl;

import com.smartosc.training.entities.ApiLog;
import com.smartosc.training.repositories.ApiLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 10/07/2020 - 5:07 PM
 * @created_by Hieupv
 * @since 10/07/2020
 */
@Service
public class ApiLogServiceImpl {
    @Autowired
    private ApiLogRepository apiLogRepository;

    public List<ApiLog> list() {
        return apiLogRepository.findAll();
    }

    public void saveApiLog(ApiLog apiLog) {
        apiLogRepository.save(apiLog);
    }
}
