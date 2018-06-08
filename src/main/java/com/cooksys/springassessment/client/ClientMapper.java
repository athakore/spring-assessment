package com.cooksys.springassessment.client;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
	ClientDto toDto(Client entity);
	Client toEntity(ClientDto dto);
	Client fromSignUpDto(SignUpDto dto);
}
