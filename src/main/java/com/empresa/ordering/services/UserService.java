package com.empresa.ordering.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.empresa.ordering.entities.User;
import com.empresa.ordering.repositories.UserRepository;
import com.empresa.ordering.services.exceptions.DatabaseException;
import com.empresa.ordering.services.exceptions.ResourceNotFoundException;

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
	
	/*Optional, vai tentar dar o get, se nao conseguir, vai retornar a exception*/
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert (User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
		repository.deleteById(id);
	} catch (EmptyResultDataAccessException e) {
		throw new ResourceNotFoundException(id);
		
	/*Manter um catch de exception mais generica para capturar erros especificos nao tratados no console para tratamento posterior (e.printStackTrace();)*/
	} catch (RuntimeException e) {
			throw new DatabaseException(e.getMessage());
//			e.printStackTrace();
		}
	}
	
	public User update(Long id, User obj) {
		try {
		User entity = repository.getOne(id); /*getOne prepara um objeto para operacao posterior*/
		updateData(entity, obj);
		return repository.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
	}

}
