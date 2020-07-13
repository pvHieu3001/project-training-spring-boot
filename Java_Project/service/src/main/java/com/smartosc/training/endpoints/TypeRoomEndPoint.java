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

import java.util.List;
import java.util.stream.Collectors;

@Endpoint
public class TypeRoomEndPoint {
    private static final String NAMESPACE_URI = "http://example.com";

    @Autowired
    private TypeRoomService typeRoomService;
    @Autowired
    private ModelMapper modelMapper;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "typeRoomRequest")
    @ResponsePayload
    public TypeRoomResponse findById(@RequestPayload TypeRoomRequest typeRoomRequest) {
        TypeRoomResponse typeRoomResponse = new TypeRoomResponse();
        TypeRoomDTO typeRoomDTO = typeRoomService.search(Long.parseLong(typeRoomRequest.getId()));
        return toTypeRoomRespone(typeRoomDTO);
    }

//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "typeRoomRequest")
//    @ResponsePayload
//    public List<TypeRoomResponse> findByName(@RequestPayload TypeRoomRequest typeRoomRequest) {
//        List<TypeRoomResponse> typeRoomResponse = typeRoomService.findTypeRoomByName(typeRoomRequest.getName())
//                .stream().map(s->toTypeRoomRespone(s)).collect(Collectors.toList());
//
//        return typeRoomResponse;
//    }

    public TypeRoomResponse toTypeRoomRespone(TypeRoomDTO typeRoomDTO){
        TypeRoomResponse typeRoomResponse = new TypeRoomResponse();
        typeRoomResponse.setImgUrl(typeRoomDTO.getImgUrl());
        typeRoomResponse.setName(typeRoomDTO.getName());
        typeRoomResponse.setTotalPrice(String.valueOf(typeRoomDTO.getTotalPrice()));
        typeRoomResponse.setId(String.valueOf(typeRoomDTO.getId()));
        return typeRoomResponse;
    }
}
