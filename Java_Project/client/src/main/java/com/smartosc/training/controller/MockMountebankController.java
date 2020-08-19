package com.smartosc.training.controller;

import com.smartosc.training.dto.APIResponse;
import com.smartosc.training.dto.UserDTO;
import com.smartosc.training.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 30/07/2020 - 2:19 PM
 * @created_by Hieupv
 * @since 30/07/2020
 */
@Controller
public class MockMountebankController {
    @Autowired
    private RestTemplateService restTemplateService;

    @GetMapping("/mountebank")
    public ResponseEntity<APIResponse<List<UserDTO>>> findByTypeRoomName(){
//        final Link selfLink = linkTo(
//                methodOn(MockMountebankController.class).findByTypeRoomName()).withSelfRel();

        String url = "http://localhost:8090/api/v1/rest/series/search";
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        //header.setBearerAuth(token.substring(7));
        List<UserDTO> data =  restTemplateService.getSomething(url, HttpMethod.GET, header, null, new ParameterizedTypeReference<APIResponse<List<UserDTO>>>() {});
        return new ResponseEntity(new APIResponse<>(
                HttpStatus.OK.value(),
                "",
                data
        ), HttpStatus.OK);
    }
}
