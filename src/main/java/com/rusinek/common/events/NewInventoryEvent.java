package com.rusinek.common.events;

import lombok.NoArgsConstructor;

/**
 * Created by Adrian Rusinek on 06.04.2020
 **/
@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent {

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
