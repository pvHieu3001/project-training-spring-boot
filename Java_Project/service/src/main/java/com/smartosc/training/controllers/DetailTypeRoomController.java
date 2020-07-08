package com.smartosc.training.controllers;

import com.smartosc.training.dto.APIResponse;
import com.smartosc.training.dto.CentralDTO;
import com.smartosc.training.dto.TypeRoomDTO;
import com.smartosc.training.services.TypeRoomService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 02/07/2020 - 2:00 PM
 * @created_by Hieupv
 * @since 02/07/2020
 */
@RestController
@RequestMapping("/api/detail-type-room")
public class DetailTypeRoomController {
    @Autowired
    private TypeRoomService typeRoomService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<List<CentralDTO>>> findById(@PathVariable("id") Long id, Locale locale){
        return new ResponseEntity(new APIResponse<>(
                HttpStatus.OK.value(),
                messageSource.getMessage("hello", null, locale),
                typeRoomService.findTypeRoomById(id)
        ), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse<TypeRoomDTO>> updateTypeRoom(@RequestBody TypeRoomDTO typeRoomDTO, Locale locale) throws NotFoundException {
        return new ResponseEntity(new APIResponse<>(
                HttpStatus.OK.value(),
                messageSource.getMessage("hello", null, locale),
                typeRoomService.updateTypeRoom(typeRoomDTO)
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse<TypeRoomDTO>> createTypeRoom(@RequestBody TypeRoomDTO typeRoomDTO, Locale locale){
        return new ResponseEntity(new APIResponse<>(
                HttpStatus.OK.value(),
                messageSource.getMessage("hello", null, locale),
                typeRoomService.createTypeRoom(typeRoomDTO)
        ), HttpStatus.OK);
    }
}
