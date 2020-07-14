package com.smartosc.training.endpoints;

import com.example.TypeRoomRequest;
import com.example.TypeRoomResponse;
import com.example.TypeRoomResponseList;
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
        return modelMapper.map(typeRoomDTO, TypeRoomResponse.class);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "typeRoomRequest")
    @ResponsePayload
    public TypeRoomResponseList findByName(@RequestPayload TypeRoomRequest typeRoomRequest) {
        TypeRoomResponseList typeRoomResponse = new TypeRoomResponseList();
        List<TypeRoomResponse> typeRoomResponses = typeRoomService.findTypeRoomByName(typeRoomRequest.getName())
                                                    .stream().map(s->modelMapper.map(s, TypeRoomResponse.class))
                                                    .collect(Collectors.toList());
        typeRoomResponse.setId(typeRoomResponses);
        return typeRoomResponse;
    }

}
