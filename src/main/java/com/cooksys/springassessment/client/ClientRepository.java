package com.cooksys.springassessment.client;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

	List<Client> getByRelativesId(Long id);

	List<Client> getByAddressId(Long id);

}
