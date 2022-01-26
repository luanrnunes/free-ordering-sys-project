package com.empresa.ordering.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.ordering.entities.Order;
import com.empresa.ordering.repositories.OrderRepository;

/*Anotation Service, registra a classe como um componente spring (especifico Service), permitindo
por exemplo, utilizar o autowired para esta classe em outra que precise depender dela
tambem existe anotation Component e Entity, especificos para as respectivas classes criadas*/

@Service   /*Indica ao JPA que a classe é um servico*/
public class OrderService {

	@Autowired /*Autowired injecao de dependencia Spring, quando trago outra classe, indico a dependencia com esta anotation*/
	private OrderRepository repository;
	
	public List<Order> findAll() {
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}
	

}
