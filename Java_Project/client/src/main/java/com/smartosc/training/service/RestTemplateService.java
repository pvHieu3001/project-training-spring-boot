package com.smartosc.training.service;

import com.smartosc.training.dto.APIResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 30/07/2020 - 2:24 PM
 * @created_by Hieupv
 * @since 30/07/2020
 */
public interface RestTemplateService {
    public <T> T getSomething(String url, HttpMethod method, HttpHeaders headers, Object body, ParameterizedTypeReference<APIResponse<T>> reponseType);
}
