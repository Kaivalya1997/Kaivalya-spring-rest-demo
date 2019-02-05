package com.sapient.order.rest.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
@Entity(name="addresses")
public class Address {
	 @Id
	 private int id;
     private String HouseNumber;
     private int pin;
}
