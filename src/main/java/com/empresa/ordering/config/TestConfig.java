package com.empresa.ordering.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.empresa.ordering.entities.Category;
import com.empresa.ordering.entities.Order;
import com.empresa.ordering.entities.OrderItem;
import com.empresa.ordering.entities.Product;
import com.empresa.ordering.entities.User;
import com.empresa.ordering.entities.enums.OrderStatus;
import com.empresa.ordering.repositories.CategoryRepository;
import com.empresa.ordering.repositories.OrderItemRepository;
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
	
	@Autowired
	private OrderItemRepository orderItemRepository;

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
		
		/*Salvando as categorias e produtos no repositorio (que vai pro banco)*/
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		/*Associando produtos com as categorias. O JPA fara a conversao de orientacao a objetos
		 * para dados relacionais (SQL)*/
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		/*Salvando os produtos e suas categorias associadas no repositorio (que vai pro banco)*/
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
		
		/*Ex: o novo objeto recebe o Order, o Pedido, a Quantidade do pedido e o preco registrado para o produto*/
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice()); 
		
		/*Salvando os OrderItem na base*/
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

			
	}

}
