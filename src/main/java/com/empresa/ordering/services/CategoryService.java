package com.empresa.ordering.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.ordering.entities.Category;
import com.empresa.ordering.repositories.CategoryRepository;

/*Anotation Service, registra a classe como um componente spring (especifico Service), permitindo
por exemplo, utilizar o autowired para esta classe em outra que precise depender dela
tambem existe anotation Component e Entity, especificos para as respectivas classes criadas*/

@Service 
public class CategoryService {

	@Autowired /*Autowired injecao de dependencia Spring, quando trago outra classe, indico a dependencia com esta anotation*/
	private CategoryRepository repository;
	
	public List<Category> findAll() {
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.get();
	}
	

}
