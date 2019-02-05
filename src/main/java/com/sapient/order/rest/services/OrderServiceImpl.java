package com.sapient.order.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.order.rest.dto.Address;
import com.sapient.order.rest.dto.Order;
import com.sapient.order.rest.repository.IAddressRepository;
import com.sapient.order.rest.repository.IOrderRepository;

@Service
public class OrderServiceImpl implements IOrderService {
	@Autowired
	private IOrderRepository orderRepositoryImpl;
	@Autowired
	private IAddressRepository addressRepositoryImpl;

	public OrderServiceImpl() {
		System.out.println("OrderServiceImpl bean created");
	}

	/**
	 * 
	 * @param order
	 * @return order id
	 * @throws Exception 
	 */
	//@Transactional(rollbackOn=Exception.class, dontRollbackOn=IllegalAccessException.class)
	@Transactional
	public int processOrder(Order order) throws Exception {
		orderRepositoryImpl.save(order);
		Address address=new Address();
		address.setHouseNumber("ABC");
		address.setPin(10);
		addressRepositoryImpl.save(address);
		
		//CRUD
		//
		return order.getId();
	}
	@Override @Transactional
    public void deleteOrder(int id) {
		orderRepositoryImpl.delete(id);
	}
	public IOrderRepository getOrderRepositoryImpl() {
		return orderRepositoryImpl;
	}
	public Order get(int id) {
		return orderRepositoryImpl.get(id);
	}
}
