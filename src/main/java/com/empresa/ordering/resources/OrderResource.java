package com.empresa.ordering.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.ordering.entities.Order;
import com.empresa.ordering.services.OrderService;

/*Para indicar que a tag é um recurso web que é implementado por
 * um controlador REST, é necessaria a anotation RestController
 * RequestMapping da o caminho onde a REST ira responder 
 * (ex, digitar no navegador localhost:8080/user ira retornar o que foi definido na classe)*/

@RestController
@RequestMapping(value="/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;
	
	/*Para indicar que este metodo responde a requisicoes GET do HTTP, é utilizado GetMapping */
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		List<Order> list = service.findAll();
		
		/*Retorna o response entity se a conexao for bem sucedida(ok), e o body de 'list' */
		return ResponseEntity.ok().body(list);
	}
	
	/*GetMapping aponta que a requisicao esperada é um get. (value = "/{id}") indica que 
	é esperado um id junto a URL nesta requisição*/
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) { /*PathVariable aponta para o Spring aceitar a id que sera passada como argumento*/
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
}
