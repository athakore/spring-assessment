package com.cooksys.springassessment.address;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cooksys.springassessment.client.Client;
import com.cooksys.springassessment.client.ClientService;
import com.cooksys.springassessment.exception.ReferencedEntityNotFoundException;

@Service
public class AddressService {
	private AddressRepository addressRepo;
	private AddressMapper addressMapper;
	private ClientService clientService;
	public AddressService(AddressRepository addressRepo, AddressMapper addressMapper, ClientService clientService) {
		this.addressRepo = addressRepo;
		this.addressMapper = addressMapper;
		this.clientService = clientService;
	}
	
	public List<AddressDto> getAll(String city, String state) {
		// TODO Auto-generated method stub
		if(city == null && state == null)
			return addressRepo.findAll().stream().map(addressMapper::toDto).collect(Collectors.toList());
		else if(city != null && state != null)
			return addressRepo.findByCityAndStateEquals(city,state).stream().map(addressMapper::toDto).collect(Collectors.toList());
		else if(city != null)
			return addressRepo.findByCity(city).stream().map(addressMapper::toDto).collect(Collectors.toList());
		else
			return addressRepo.findByState(state).stream().map(addressMapper::toDto).collect(Collectors.toList());
	}
	
	public AddressDto getById(Long id) {
		mustExist(id);
		return addressMapper.toDto(addressRepo.getOne(id));
	}
	
	public Long post(AddressDto addressDto) {
		return addressRepo.save(addressMapper.toEntity(addressDto)).getId();
	}
	
	public Long put(Long id, AddressDto addressDto) {
		mustExist(id);
		addressDto.setId(id);
		return addressRepo.save(addressMapper.toEntity(addressDto)).getId();
	}
	
	public void delete(Long id) {
		mustExist(id);
		addressRepo.delete(addressRepo.getOne(id));
		
	}
	
	public AddressDto getByUserId(Long id) {
		clientService.mustExist(id);
		return addressMapper.toDto(addressRepo.findByResidentsId(id));
	}

	public void mustExist(Long id) {
		if(!has(id))
			throw new ReferencedEntityNotFoundException(Address.class, id);
	}

	public boolean has(Long id) {
		return addressRepo.existsById(id);
	}
}
