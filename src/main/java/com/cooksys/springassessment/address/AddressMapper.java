package com.cooksys.springassessment.address;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	AddressDto toDto(Address entity);
	Address toEntity(AddressDto dto);
}
