package com.smartosc.training.controllers;

import com.smartosc.training.dto.CentralDTO;
import com.smartosc.training.dto.response.APIResponse;
import com.smartosc.training.entities.Central;
import com.smartosc.training.services.CentralService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

  @Autowired
  private CentralService centralService;

  @GetMapping
  public ResponseEntity<APIResponse<List<CentralDTO>>> getAllCentral(
      @RequestParam(value = "keyword", required = false) String keyword
  ) {

    List<CentralDTO> centralDTOS = centralService.getAllCentral(keyword);

    APIResponse apiResponse = new APIResponse();
    apiResponse.setStatus(HttpStatus.OK.toString());
    apiResponse.setData(centralDTOS);
    apiResponse.setMessage("Lay ra thanh cong roi day :) ");

    return new ResponseEntity(apiResponse, HttpStatus.OK);
  }

}
