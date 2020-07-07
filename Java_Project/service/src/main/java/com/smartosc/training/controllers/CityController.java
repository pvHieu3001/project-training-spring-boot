package com.smartosc.training.controllers;

import com.smartosc.training.dto.APIResponse;
import com.smartosc.training.dto.CityDTO;
import com.smartosc.training.entities.Central;
import com.smartosc.training.entities.Hotel;
import com.smartosc.training.services.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        apiResponse.setMessage("message.getAll.success");
        apiResponse.setMessage(messageSource.getMessage("message.getAll.success",null, locale));
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<CityDTO>> getCityWithHotels(@PathVariable("id") Long id, Locale locale) {
        APIResponse<CityDTO> apiResponse = new APIResponse<>();

        CityDTO result = cityService.getCityWithHotels(id);
        apiResponse.setData(result);
        apiResponse.setMessage(messageSource.getMessage("message.getById.success",null, locale));
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/menu")
    public ResponseEntity<APIResponse<Page<CityDTO>>> getWithPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                                 @RequestParam(value = "size") int size, Locale locale) {

        APIResponse<Page<CityDTO>> apiResponse = new APIResponse<>();

        Pageable pageable = PageRequest.of(page,size);

        Page<CityDTO> result = cityService.getCitiesWithPagination(pageable);
        apiResponse.setData(result);
        apiResponse.setMessage(messageSource.getMessage("message.getAll.success",null, locale));
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<APIResponse<CityDTO>> createNew(@RequestBody CityDTO input, Locale locale) {
        APIResponse<CityDTO> apiResponse = new APIResponse<>();

        CityDTO result = cityService.createNew(input);
        apiResponse.setData(result);
        apiResponse.setMessage(messageSource.getMessage("message.create.success",null, locale));
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<APIResponse<CityDTO>> updateInformation(@RequestBody CityDTO input, Locale locale) {
        APIResponse<CityDTO> apiResponse = new APIResponse<>();

        CityDTO result = cityService.updateInformation(input);
        apiResponse.setData(result);
        apiResponse.setMessage(messageSource.getMessage("message.create.success",null, locale));
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
