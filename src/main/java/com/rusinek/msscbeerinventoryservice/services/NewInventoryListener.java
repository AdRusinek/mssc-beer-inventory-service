package com.rusinek.msscbeerinventoryservice.services;

import com.rusinek.brewery.model.events.NewInventoryEvent;
import com.rusinek.msscbeerinventoryservice.domain.BeerInventory;
import com.rusinek.msscbeerinventoryservice.repositories.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static com.rusinek.msscbeerinventoryservice.config.JmsConfig.NEW_INVENTORY_QUEUE;

/**
 * Created by Adrian Rusinek on 07.04.2020
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class NewInventoryListener {

    private final BeerInventoryRepository beerInventoryRepository;

    @JmsListener(destination = NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent event){
        log.debug("Got Inventory: " + event.toString());

        beerInventoryRepository.save(BeerInventory.builder()
                .beerId(event.getBeerDto().getId())
                .upc(event.getBeerDto().getUpc())
                .quantityOnHand(event.getBeerDto().getQuantityOnHand())
                .build());
    }

}
