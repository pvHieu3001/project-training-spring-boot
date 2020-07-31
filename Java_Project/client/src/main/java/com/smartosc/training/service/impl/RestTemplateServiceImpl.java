package com.smartosc.training.service.impl;

import com.smartosc.training.dto.APIResponse;
import com.smartosc.training.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 30/07/2020 - 2:25 PM
 * @created_by Hieupv
 * @since 30/07/2020
 */
@Service
public class RestTemplateServiceImpl implements RestTemplateService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public <T> T getSomething(String url, HttpMethod method, HttpHeaders headers, Object body, ParameterizedTypeReference<APIResponse<T>> reponseType) {

            HttpEntity<Object> entity = new HttpEntity<>(body, headers);
            ResponseEntity<APIResponse<T>> res = restTemplate.exchange(url, method, entity, reponseType);
            if (res.getStatusCode().value() >= HttpStatus.OK.value() && res.getStatusCode().value() < HttpStatus.MULTIPLE_CHOICES.value()) {
                return res.getBody().getData();
            }
            throw new RestClientException(res.getBody().getMessage());

    }

}
