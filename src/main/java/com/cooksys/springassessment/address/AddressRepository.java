package com.cooksys.springassessment.address;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	Collection<Address> findByCityAndStateEquals(String city, String state);

	Collection<Address> findByCity(String city);

	Collection<Address> findByState(String state);

	Address findByResidentsId(Long id);

}
