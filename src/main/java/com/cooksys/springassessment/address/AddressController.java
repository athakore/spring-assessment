package com.cooksys.springassessment.address;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.springassessment.client.ClientDto;
import com.cooksys.springassessment.client.ClientService;

@RestController
@RequestMapping("address")
public class AddressController {
	private AddressService addressService;
	private ClientService clientService;

	public AddressController(AddressService addressService, ClientService clientService) {
		this.addressService = addressService;
		this.clientService = clientService;
	}
	
	@GetMapping
	public List<AddressDto> getAll(@RequestParam(value = "city", required = false) String city, @RequestParam(value = "state", required = false) String state) {
		return addressService.getAll(city, state);
	}
	
	@GetMapping("{id}")
	public AddressDto getById(@PathVariable Long id) {
		return addressService.getById(id);
	}
	
	@GetMapping("{id}/residents")
	public List<ClientDto> getResidents(@PathVariable Long id) {
		return clientService.getByAddressId(id);
	}
	
	@PostMapping
	public Long post(@RequestBody @Validated AddressDto addressDto) {
		return addressService.post(addressDto);
	}
	
	@PutMapping("{id}")
	public Long put(@PathVariable Long id, @RequestBody @Validated AddressDto addressDto) {
		return addressService.put(id, addressDto);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		addressService.delete(id);
	}
}
