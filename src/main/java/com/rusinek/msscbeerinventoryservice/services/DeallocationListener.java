package com.rusinek.msscbeerinventoryservice.services;

import com.rusinek.brewery.model.events.DeallocateOrderRequest;
import com.rusinek.msscbeerinventoryservice.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by Adrian Rusinek on 09.04.2020
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DeallocationListener {

    private final AllocationService allocationService;

    @JmsListener(destination = JmsConfig.DEALLOCATE_ORDER_QUEUE)
    public void listen(DeallocateOrderRequest request) {
        allocationService.deallocateOrder(request.getBeerOrderDto());
    }
}
