package com.smartosc.training.controllers;

import com.smartosc.training.dto.APIResponse;
import com.smartosc.training.dto.CityDTO;
import com.smartosc.training.entities.Central;
import com.smartosc.training.entities.Hotel;
import com.smartosc.training.services.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 03/07/2020 - 2:20 PM
 */
@RestController
@RequestMapping("/api/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public ResponseEntity<APIResponse<List<CityDTO>>> getAllCities(Locale locale) {
        APIResponse<List<CityDTO>> objectAPIResponse = new APIResponse<>();
        List<CityDTO> result = cityService.getAllCities();
        objectAPIResponse.setData(result);
        objectAPIResponse.setMessage(messageSource.getMessage("Messsage.status.ok",null, locale));
        objectAPIResponse.setStatus(HttpStatus.OK.toString());
        return new ResponseEntity<>(objectAPIResponse, HttpStatus.OK);
    }
}
