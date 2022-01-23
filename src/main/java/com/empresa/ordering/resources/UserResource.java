package com.empresa.ordering.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.ordering.entities.User;

/*Para indicar que a tag é um recurso web que é implementado por
 * um controlador REST, é necessaria a anotation RestController
 * RequestMapping da o caminho onde a REST ira responder 
 * (ex, digitar no navegador localhost:8080/user ira retornar o que foi definido na classe)*/

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	/*Para indicar que este metodo responde a requisicoes GET do HTTP, é utilizado GetMapping */
	
	@GetMapping
	public ResponseEntity<User> findAll() {
		User u = new User(1L, "Maria", "maria@gmail.com","99999999","12345");
		/*Retorna o response entity se a conexao for bem sucedida(ok), e o body do 'u' */
		return ResponseEntity.ok().body(u);
	}
}
