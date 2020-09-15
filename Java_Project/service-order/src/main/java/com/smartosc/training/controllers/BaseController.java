package com.smartosc.training.controllers;

import com.smartosc.training.base.BaseService;
import com.smartosc.training.base.IdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

public abstract class BaseController<T extends BaseEntity, DTO extends IdDto, ID extends Serializable> {

    @Autowired
    public abstract BaseService<T, DTO, ID> getService();

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        List<T> models = getService().findAll();
        return new ResponseEntity<Object>(models, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody DTO dto) {
        T model = getService().save(dto);
        return new ResponseEntity<Object>(model, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody DTO dto) {
        T model = getService().update(dto);
        return new ResponseEntity<Object>(model, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable ID id) {
        getService().delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<?> view(@PathVariable ID id) {
        T model = getService().findById(id);
        return new ResponseEntity<Object>(model, HttpStatus.OK);
    }

}
