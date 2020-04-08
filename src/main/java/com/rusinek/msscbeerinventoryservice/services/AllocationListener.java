package com.rusinek.msscbeerinventoryservice.services;

import com.rusinek.brewery.model.events.AllocateOrderRequest;
import com.rusinek.brewery.model.events.AllocateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import static com.rusinek.msscbeerinventoryservice.config.JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE;

/**
 * Created by Adrian Rusinek on 09.04.2020
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class AllocationListener {

    private final AllocationService allocationService;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = ALLOCATE_ORDER_RESPONSE_QUEUE)
    public void listen(AllocateOrderRequest request){
        AllocateOrderResult.AllocateOrderResultBuilder builder = AllocateOrderResult.builder();
        builder.beerOrderDto(request.getBeerOrderDto());

        try{
            Boolean allocationResult = allocationService.allocateOrder(request.getBeerOrderDto());

            if (allocationResult){
                builder.pendingInventory(false);
            } else {
                builder.pendingInventory(true);
            }

            builder.allocationError(false);
        } catch (Exception e){
            log.error("Allocation failed for Order Id:" + request.getBeerOrderDto().getId());
            builder.allocationError(true);
        }

        jmsTemplate.convertAndSend(ALLOCATE_ORDER_RESPONSE_QUEUE,
                builder.build());

    }
}
