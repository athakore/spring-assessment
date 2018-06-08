package com.cooksys.springassessment.client;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cooksys.springassessment.exception.ReferencedEntityNotFoundException;
import com.cooksys.springassessment.exception.SelfRelationException;
import com.cooksys.springassessment.address.Address;
import com.cooksys.springassessment.address.AddressRepository;

@Service
public class ClientService {
	private ClientRepository clientRepo;
	private ClientMapper clientMapper;
	private AddressRepository addressRepo;
	public ClientService(ClientRepository clientRepo, ClientMapper clientMapper, AddressRepository addressRepo) {
		this.clientRepo = clientRepo;
		this.clientMapper = clientMapper;
		this.addressRepo = addressRepo;
	}
	public List<ClientDto> getAll() {
		return clientRepo.findAll().stream().map(clientMapper::toDto).collect(Collectors.toList());
	}

	public ClientDto getById(Long id) {
		mustExist(id);
		return clientMapper.toDto(clientRepo.getOne(id));
	}

	public List<ClientDto> getRelatives(Long id) {
		mustExist(id);
		return clientRepo.getByRelativesId(id).stream().map(clientMapper::toDto).collect(Collectors.toList());
	}

	public Long post(SignUpDto clientDto) {
		// TODO Auto-generated method stub
		return clientRepo.save(clientMapper.fromSignUpDto(clientDto)).getId();
	}

	public Long postRelative(Long id, Long relationId) throws SelfRelationException {
		mustExist(id);
		mustExist(relationId);
		if(id.equals(relationId))
			throw new SelfRelationException(id);
		Client temp = clientRepo.getOne(id);
		temp.getRelatives().add(clientRepo.getOne(relationId));
		clientRepo.save(temp);
		return relationId;
	}

	public Long postAddress(Long id, Long addressId) {
		mustExist(id);
		addressMustExist(addressId);
		Client temp = clientRepo.getOne(id);
		temp.setAddress(addressRepo.getOne(addressId));
		clientRepo.save(temp);
		return addressId;
	}

	public Long put(Long id, SignUpDto clientDto) {
		mustExist(id);
		clientDto.setId(id);
		return clientRepo.save(clientMapper.fromSignUpDto(clientDto)).getId();
		
	}

	public void delete(Long id) {
		mustExist(id);
		clientRepo.delete(clientRepo.getOne(id));
		
	}

	public List<ClientDto> getByAddressId(Long id) {
		addressMustExist(id);
		return clientRepo.getByAddressId(id).stream().map(clientMapper::toDto).collect(Collectors.toList());
	}

	public void mustExist(Long id) {
		if(!has(id))
			throw new ReferencedEntityNotFoundException(Client.class, id);
	}

	public boolean has(Long id) {
		return clientRepo.existsById(id);
	}

	public void addressMustExist(Long id) {
		if(!has(id))
			throw new ReferencedEntityNotFoundException(Address.class, id);
	}

	public boolean addressHas(Long id) {
		return addressRepo.existsById(id);
	}
}
