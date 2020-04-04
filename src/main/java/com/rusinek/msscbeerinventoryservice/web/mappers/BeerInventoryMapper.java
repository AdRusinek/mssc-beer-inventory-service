package com.rusinek.msscbeerinventoryservice.web.mappers;

import com.rusinek.msscbeerinventoryservice.domain.BeerInventory;
import com.rusinek.msscbeerinventoryservice.web.model.BeerInventoryDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerInventoryMapper {

    BeerInventory beerInventoryDtoToBeerInventory(BeerInventoryDto beerInventoryDTO);

    BeerInventoryDto beerInventoryToBeerInventoryDto(BeerInventory beerInventory);
}
