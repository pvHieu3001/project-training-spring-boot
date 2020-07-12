package com.smartosc.training.endpoints;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class TypeRoomEndPoint {
    private static final String NAMESPACE_URI = "http://example.com";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "calculationRequest")
    @ResponsePayload
    public CalculationResponse calculationResponse(@RequestPayload CalculationRequest calculationRequest) {
        CalculationResponse calculationResponse = new CalculationResponse();
        calculationResponse.setTotal(calculationRequest.getA() + calculationRequest.getB());
        return calculationResponse;
    }
}
