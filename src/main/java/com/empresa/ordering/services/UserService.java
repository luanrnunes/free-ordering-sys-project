package com.empresa.ordering.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.ordering.entities.User;
import com.empresa.ordering.repositories.UserRepository;

/*Anotation Service, registra a classe como um componente spring (especifico Service), permitindo
por exemplo, utilizar o autowired para esta classe em outra que precise depender dela
tambem existe anotation Component e Entity, especificos para as respectivas classes criadas*/

@Service 
public class UserService {

	@Autowired /*Autowired injecao de dependencia Spring, quando trago outra classe, indico a dependencia com esta anotation*/
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
	
	public User insert (User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
