package com.smartosc.training.events;

import org.springframework.kafka.support.serializer.JsonDeserializer;

public class OrderDeserializer extends JsonDeserializer<OrderDB> {

	public OrderDeserializer() {
		super(OrderDB.class);
	}

}
