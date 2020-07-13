package com.smartosc.training.endpoints;

import com.example.TypeRoomRequest;
import com.example.TypeRoomResponse;
import com.smartosc.training.dto.TypeRoomDTO;
import com.smartosc.training.services.TypeRoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class TypeRoomEndPoint {
    private static final String NAMESPACE_URI = "http://example.com";

    @Autowired
    private TypeRoomService typeRoomService;
    @Autowired
    private ModelMapper modelMapper;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "typeRoomRequest")
    @ResponsePayload
    public TypeRoomResponse calculationResponse(@RequestPayload TypeRoomRequest typeRoomRequest) {
        TypeRoomResponse typeRoomResponse = new TypeRoomResponse();
        TypeRoomDTO typeRoomDTO =  typeRoomService.findTypeRoomByName(typeRoomRequest.getName());
        typeRoomResponse.setImgUrl(typeRoomDTO.getImgUrl());
        typeRoomResponse.setName(typeRoomDTO.getName());
        typeRoomResponse.setTotalPrice(String.valueOf(typeRoomDTO.getTotalPrice()));
        typeRoomResponse.setId(String.valueOf(typeRoomDTO.getId()));
        return typeRoomResponse;
    }
}
