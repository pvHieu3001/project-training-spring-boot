package com.smartosc.training.controllers;

import com.smartosc.training.services.TypeRoomService;
import com.smartosc.training.services.impl.TypeRoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}")
    public Object index(@PathVariable("id") Long id){
        return typeRoomService.findTypeRoomById(id);
    }
}
