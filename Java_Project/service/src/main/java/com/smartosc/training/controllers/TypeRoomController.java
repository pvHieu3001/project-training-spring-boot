package com.smartosc.training.controllers;

import com.smartosc.training.dto.APIResponse;
import com.smartosc.training.dto.CentralDTO;
import com.smartosc.training.dto.TypeRoomDTO;
import com.smartosc.training.services.TypeRoomService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
@RequestMapping("/api/type-room")
public class TypeRoomController {
    @Autowired
    private TypeRoomService typeRoomService;

    @Autowired
    private MessageSource messageSource;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "page number", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "size of page", defaultValue = "20"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "sort by")
    })
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<List<TypeRoomDTO>>> findById(@PathVariable("id") Long id, Locale locale, @ApiIgnore Pageable pageable){
        return new ResponseEntity(new APIResponse<>(
                HttpStatus.OK.value(),
                messageSource.getMessage("msg.success", null, locale),
                typeRoomService.findTypeRoomById(id, pageable)
        ), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse<TypeRoomDTO>> updateTypeRoom(@RequestBody TypeRoomDTO typeRoomDTO, Locale locale) throws NotFoundException {
        return new ResponseEntity(new APIResponse<>(
                HttpStatus.OK.value(),
                messageSource.getMessage("msg.success", null, locale),
                typeRoomService.updateTypeRoom(typeRoomDTO)
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse<TypeRoomDTO>> createTypeRoom(@RequestBody TypeRoomDTO typeRoomDTO, Locale locale){
        return new ResponseEntity(new APIResponse<>(
                HttpStatus.OK.value(),
                messageSource.getMessage("msg.success", null, locale),
                typeRoomService.createTypeRoom(typeRoomDTO)
        ), HttpStatus.OK);
    }
}
