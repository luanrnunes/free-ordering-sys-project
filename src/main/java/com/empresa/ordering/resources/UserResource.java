package com.empresa.ordering.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.empresa.ordering.entities.User;
import com.empresa.ordering.services.UserService;

/*Para indicar que a tag é um recurso web que é implementado por
 * um controlador REST, é necessaria a anotation RestController
 * RequestMapping da o caminho onde a REST ira responder 
 * (ex, digitar no navegador localhost:8080/user ira retornar o que foi definido na classe)*/


@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	/*Para indicar que o metodo é a resposta GET do HTTP, é utilizado GetMapping */
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		
		/*Retorna o response entity se a conexao for bem sucedida(ok), e o body de 'list' */
		return ResponseEntity.ok().body(list);
	}
	
	/*GetMapping aponta que a requisicao esperada é um get. (value = "/{id}") indica que 
	é esperado um id junto a URL nesta requisição*/
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) { /*PathVariable aponta para o Spring aceitar a id que sera passada como argumento*/
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/*PostMapping. Notation do spring que faz um pre processamento na compilacao e indica que vai responder
	a uma requisicao post do HTTP*/
	
	@PostMapping 
	public ResponseEntity<User> insert (@RequestBody User obj) { /*RequestBody indica que a resposta sera um JSON que precisa ser desserializado, neste caso, desserializado para um objeto User*/
		obj = service.insert(obj);
		/*Para retornar com o codigo 201 e um header ao inves do padrao 200*/
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	/*DeleteMapping notation do spring que indica que o recurso pode receber uma requisicao de delete*/
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) { /*PathVariable indica que o argumento passado na requisicao HTTP sera aceito*/
	service.delete(id);
	return ResponseEntity.noContent().build(); /*Indica que a resposta desta requisicao sera vazia, retornando um 204*/
}
	
}
