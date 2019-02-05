package com.sapient.order.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.order.rest.dto.Order;
import com.sapient.order.rest.services.IOrderService;

@RestController("/orders")
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private IOrderService orderService;
//	@RequestMapping(method=RequestMethod.POST)
	@PostMapping
	public ResponseEntity<String> createOrder(@RequestBody Order order) {
		logger.info("creating order...{}", order);
		ResponseEntity<String> responseEntity;
		try {
			orderService.processOrder(order);
			responseEntity=new ResponseEntity<String>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity=new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "orders/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete1(@PathVariable int id) {
		logger.info("delete order with id={}",id);
		ResponseEntity<String> responseEntity1 = null;
		try {
		orderService.deleteOrder(id);
		responseEntity1=new ResponseEntity<String>(HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			responseEntity1=new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return responseEntity1;
	}
	@GetMapping("/orders/{id}")	
	public ResponseEntity get(@PathVariable int id) {
		ResponseEntity responseEntity2;
		Order order=orderService.get(id);
		if(order==null) {
			return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
		}
		responseEntity2=ResponseEntity.ok(order);
		return responseEntity2;
	}
}
