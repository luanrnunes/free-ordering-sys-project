package com.empresa.ordering.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.empresa.ordering.entities.User;
import com.empresa.ordering.repositories.UserRepository;

/*Configuration é a anotation para o spring de que se trata de uma classe de configuracao*/

/*Anotaton Profile, indica que este arquivo so sera considerando quando for usado o perfil
'test', definido em application.properties*/

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired /*Anotation Wired faz com que o Spring resolva as dependencias desejadas
	 e associar uma instancia de, neste caso, UserRepository aqui dentro*/
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		/*O que fica dentro do metodo run é o que será executado primeiro quando a aplicacao inicializar*/
		
			User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
			User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 

			/*Salvando os users no banco*/
			userRepository.saveAll(Arrays.asList(u1, u2));
	}
	
	

}
