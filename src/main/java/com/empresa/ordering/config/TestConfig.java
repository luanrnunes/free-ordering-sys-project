package com.empresa.ordering.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.empresa.ordering.entities.Category;
import com.empresa.ordering.entities.Order;
import com.empresa.ordering.entities.Product;
import com.empresa.ordering.entities.User;
import com.empresa.ordering.entities.enums.OrderStatus;
import com.empresa.ordering.repositories.CategoryRepository;
import com.empresa.ordering.repositories.OrderRepository;
import com.empresa.ordering.repositories.ProductRepository;
import com.empresa.ordering.repositories.UserRepository;

/*Configuration é a Notation para o spring de que se trata de uma classe de configuracao*/

/*Notaton Profile, indica que este arquivo so sera considerando quando for usado o perfil
'test', definido em application.properties*/

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired /*
				 * Anotation Wired faz com que o Spring resolva as dependencias desejadas e
				 * associar uma instancia de, neste caso, UserRepository aqui dentro
				 */
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {
		/*
		 * O que fica dentro do metodo run é o que será executado primeiro quando a
		 * aplicacao inicializar
		 */
		
	
		
		/*Instancia manual de categorias para teste*/
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, ""); 


		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		/* Instancia manual de usuarios para teste */
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

		/* Instancia manual de pedidos para teste */
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);

		/* Salvando no banco */
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
	}

}
