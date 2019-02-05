package com.sapient.order.rest.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.order.rest.dto.Address;

@Component
public class AddressRepositoryImpl implements IAddressRepository {
	@PersistenceContext
	EntityManager entityManager;
	
	@Override @Transactional(propagation=Propagation.REQUIRES_NEW)
	public void save(Address address) {
		entityManager.persist(address);
//		if(true)
//			throw new RuntimeException();
	}

}
