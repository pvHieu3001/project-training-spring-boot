package com.smartosc.training.controllers;

import com.smartosc.training.dto.APIResponse;
import com.smartosc.training.dto.TypeRoomDTO;
import com.smartosc.training.services.TypeRoomService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
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
    @Cacheable(value = "typeroom", key = "#id")
    @GetMapping(value = "/{id}", produces = { "application/hal+json" })
    public APIResponse<List<TypeRoomDTO>> findTypeRoomById(@PathVariable("id") Long id, Locale locale, @ApiIgnore Pageable pageable){

        return new APIResponse<>(
                HttpStatus.OK.value(),
                messageSource.getMessage("msg.success", null, locale),
                typeRoomService.findTypeRoomById(id, pageable).getContent()
        );
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<APIResponse<List<TypeRoomDTO>>> findByTypeRoomName(@PathVariable("name") String name, Locale locale){
        return new ResponseEntity(new APIResponse<>(
                HttpStatus.OK.value(),
                messageSource.getMessage("msg.success", null, locale),
                typeRoomService.findTypeRoomByName(name)
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
