package com.smartosc.training.controllers;

import com.smartosc.training.dto.APIResponse;
import com.smartosc.training.dto.CentralDTO;
import com.smartosc.training.services.CentralService;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 06/07/2020 - 10:47 AM
 * @created_by Namtt
 * @since 06/07/2020
 */
@RestController
@RequestMapping(value = "/api_v1/central")
public class CentralController {

  @Autowired private CentralService centralService;
  @Autowired private MessageSource messageSource;

  @GetMapping
  public ResponseEntity<APIResponse<List<CentralDTO>>> getAllCentral(
      @RequestParam(value = "keyword", required = false) String keyword,
      @RequestParam(value = "id", required = false) Long id,
      Locale locale) {

    List<CentralDTO> centralDTOS = centralService.getAllCentral(keyword, id);

    return new ResponseEntity<>(
        new APIResponse<>(
            HttpStatus.OK.value(),
            messageSource.getMessage("Get.Central.Success", null, locale),
            centralDTOS),
        HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<APIResponse<CentralDTO>> updateCentral(
      @PathVariable("id") Long id, @Valid @RequestBody CentralDTO centralDTO, Locale locale) {
    CentralDTO central = centralService.updateCentral(id, centralDTO);
    return new ResponseEntity<>(
        new APIResponse<>(
            HttpStatus.OK.value(),
            messageSource.getMessage("Update.Central.Success", null, locale),
            central),
        HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<APIResponse<CentralDTO>> createCentral(
      @Valid @RequestBody CentralDTO centralDTO, Locale locale) {
    CentralDTO central = centralService.createCentral(centralDTO);
    return new ResponseEntity<>(
        new APIResponse<>(
            HttpStatus.OK.value(),
            messageSource.getMessage("Create.Central.Success", null, locale),
            central),
        HttpStatus.OK);
  }
}
