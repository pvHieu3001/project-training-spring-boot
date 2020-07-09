package com.smartosc.training.controllers;


import com.smartosc.training.dto.APIResponse;
import com.smartosc.training.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import com.smartosc.training.dto.HotelDTO;
import org.springframework.context.MessageSource;
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
@RequestMapping("/api/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public ResponseEntity<APIResponse<List<HotelDTO>>> getAllHotels(Locale locale) {
        APIResponse<List<HotelDTO>> objectAPIResponse = new APIResponse<>();
        List<HotelDTO> result = hotelService.getAllHotels();
        objectAPIResponse.setData(result);
        objectAPIResponse.setMessage(messageSource.getMessage("message.getAll.success",null, locale));
        objectAPIResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(objectAPIResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<HotelDTO>> getHotelByID(@PathVariable("id") Long id, Locale locale) {
        APIResponse<HotelDTO> apiResponse = new APIResponse<>();
        HotelDTO result = hotelService.getHotelByID(id);
        apiResponse.setData(result);
        apiResponse.setMessage(messageSource.getMessage("message.getById.success",null, locale));
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<APIResponse<HotelDTO>> createNewHotel(@RequestBody HotelDTO input, Locale locale) {
        APIResponse<HotelDTO> apiResponse = new APIResponse<>();

        HotelDTO result = hotelService.createNew(input);
        apiResponse.setData(result);
        apiResponse.setMessage(messageSource.getMessage("message.create.success",null, locale));
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse<HotelDTO>> updateInformation(@RequestBody HotelDTO input, Locale locale) {
        APIResponse<HotelDTO> apiResponse = new APIResponse<>();

        HotelDTO result = hotelService.updateHotel(input);
        apiResponse.setData(result);
        apiResponse.setMessage(messageSource.getMessage("message.update.success",null, locale));
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteHotelById(@PathVariable("id") Long id) {
        hotelService.deleteHotel(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
