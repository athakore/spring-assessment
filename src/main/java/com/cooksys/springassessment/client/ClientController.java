package com.cooksys.springassessment.client;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.springassessment.address.AddressDto;
import com.cooksys.springassessment.address.AddressService;
import com.cooksys.springassessment.exception.SelfRelationException;

@RestController
@RequestMapping("user")
public class ClientController {
	private ClientService clientService;
	private AddressService addressService;
	
	public ClientController(ClientService clientService, AddressService addressService) {
		this.clientService = clientService;
		this.addressService = addressService;
	}
	
	@GetMapping
	public List<ClientDto> getAll() {
		return clientService.getAll();
	}
	
	@GetMapping("{id}")
	public ClientDto getById(@PathVariable Long id) {
		return clientService.getById(id);
	}
	
	@GetMapping("{id}/address")
	public AddressDto getAddress(@PathVariable Long id) {
		return addressService.getByUserId(id);
	}
	
	@GetMapping("{id}/relations")
	public List<ClientDto> getRelatives(@PathVariable Long id) {
		return clientService.getRelatives(id);
	}
	
	@PostMapping
	public Long post(@RequestBody @Validated SignUpDto clientDto) {
		return clientService.post(clientDto);
	}
	
	@PostMapping("{id}/relations/{relationId}")
	public Long postRelative(@PathVariable Long id, @PathVariable Long relationId) throws SelfRelationException {
		return clientService.postRelative(id, relationId);
	}
	
	@Transactional
	@PostMapping("{id}/address/{addressId}")
	public Long postAddress(@PathVariable Long id, @PathVariable Long addressId) {
		return clientService.postAddress(id, addressId);
	}
	
	@Transactional
	@PutMapping("{id}")
	public void put(@PathVariable Long id, @RequestBody SignUpDto clientDto) {
		clientService.put(id, clientDto);
	}
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		clientService.delete(id);
	}
}