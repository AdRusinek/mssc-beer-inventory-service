package com.rusinek.msscbeerinventoryservice.services;

import com.rusinek.brewery.model.BeerOrderDto;

/**
 * Created by Adrian Rusinek on 08.04.2020
 **/
public interface AllocationService {

    Boolean allocateOrder(BeerOrderDto beerOrderDto);

    void deallocateOrder(BeerOrderDto beerOrderDto);
}
