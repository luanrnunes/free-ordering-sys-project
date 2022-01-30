package com.empresa.ordering.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.empresa.ordering.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_order")    /*Table é opcional, indica um nome ao JPA para ser criada a tabela, ao inves de usar o nome da classe*/

public class Order implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT") /*Forca o horario Json no formato que quero definir, neste caso, ISO8601*/
	private Instant moment;
	
	/*OrderStatus foi definido como integer apenas nesta classe, para explicitar
	 * o tratamento de inteiro com o banco... O resto ira enviar como um objeto OrderStatus*/
	private Integer orderStatus;
	
	/*No diagrama diz que e um cliente para uma serie de pedidos, bastando uma unica instancia de USER*/
	
	@ManyToOne      /*ManyToOne diz ao JPA que para este objeto deve ser gerada chave estrangeira*/
	@JoinColumn(name = "client_id")   /*JoinColumn define o nome da chave estrangeira para a tabela em questao que contem o ID do usuario associado ao pedido*/
	private User client;
	
	@OneToMany(mappedBy = "id.order")  /*id.order pq no orderitem eu tenho o id e o id por sua vez é o que tem o order, por isso id.order*/
	private Set<OrderItem> items = new HashSet<>();
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL) /*Nas relacoes de um para um, é necessário definir o mesmo Id para as entidades, por ex: Se o pedido for codigo 5 o pagamento tambem
	vai ter codigo 5... cascade = CascadeType.ALL é obrigatorio para este modelo */
	private Payment payment;
	
	public Order() {
		
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		/*Assim vai apenas setar o Order Status com qualquer valor que receba, me habilitando a fazer os tratamentos com integers, 
		 * sem esperar receber especificamente um objeto do tipo OrderStatus */
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	
	/*Tratamento no get de OrderStatus para converter um inteiro para OrderStatus*/
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	/*Tratamento no set de OrderStatus para receber um orderStatus e converter para inteiro*/
	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			
		}
		this.orderStatus = orderStatus.getCode();
		
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<OrderItem> getItems() {
		return items;
	}
	
	public Double getTotal() {
		double sum = 0.0;
		for (OrderItem x : items) {
			sum += x.getSubTotal();
		}
		return sum;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
