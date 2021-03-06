package com.empresa.ordering.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tb_product")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	
	/*Utilizado set ao inves de list, pois list nao vai permitir registros duplicados
	 * de categoria, visto que um produto nao pode ter mais de uma categoria*/
	
	/*New HashSet para forcar a nao iniciar nulo, forcar a instanciar o obj*/
	
	/*Como o Set é uma interface, ele nao pode ser instanciado, deve ser usada uma classe correspondente
	 * a esta interface, neste caso HashSet*/
	
	/*Abaixo fiz as associacoes necessarias entre as tabelas, pode ser feito em uma unica classe desde
	 * que ja se relacionem... inverseJoinColumns define a chave estrangeira da outra entidade, como estou
	 * na product, a outra entidade de relacao é a categoria, portanto estou definindo o nome da FK para categoria*/
	
	@ManyToMany   /*Anotation JPA de relacao muitos pra muitos*/
	@JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), 
	inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();
	
	@OneToMany(mappedBy = "id.product") /*id pego na classe OrderItem e o product vem da classe OrderItemPK*/
	private Set<OrderItem> items = new HashSet<>();
	
	public Product() {
		
	}

	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		/*Nao foi criado constutor para categories, pois para colecoes nao se geram constructors
		 * Tambem, ela ja esta sendo instanciada la em cima*/
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
	}
	
	@JsonIgnore /*Neste JsonIgnore apenas nao quero que ao consultar os produtos, traga os pedidos, 
	apenas se for explicitamente solicitado. ex: localhost:8080/orders/1 ao inves de /products */ 
	public Set<Order> getOrders() {
		Set<Order> set = new HashSet<>();
		for (OrderItem x : items) {
			set.add(x.getOrder());
		}
		return set;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

	
	
}
