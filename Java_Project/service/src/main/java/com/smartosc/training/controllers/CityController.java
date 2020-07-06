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
import org.springframework.web.bind.annotation.PathVariable;
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
        APIResponse<List<CityDTO>> apiResponse = new APIResponse<>();
        List<CityDTO> result = cityService.getAllCities();
        apiResponse.setData(result);
        apiResponse.setMessage(messageSource.getMessage("message.status.ok",null, locale));
        apiResponse.setStatus(HttpStatus.OK.toString());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<CityDTO>> getCityWithHotels(@PathVariable("id") Long id, Locale locale) {
        APIResponse<CityDTO> apiResponse = new APIResponse<>();

        CityDTO result = cityService.getCityWithHotels(id);
        apiResponse.setData(result);
        apiResponse.setMessage(messageSource.getMessage("message.status.ok",null, locale));
        apiResponse.setStatus(HttpStatus.OK.toString());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
