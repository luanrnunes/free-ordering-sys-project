package com.empresa.ordering.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	/*No diagrama diz que e um cliente para uma serie de pedidos, bastando uma unica instancia de USER*/
	
	@ManyToOne      /*ManyToOne diz ao JPA que para este objeto deve ser gerada chave estrangeira*/
	@JoinColumn(name = "client_id")   /*JoinColumn define o nome da chave estrangeira no banco*/
	private User client;
	
	public Order() {
		
	}

	public Order(Long id, Instant moment, User client) {
		super();
		this.id = id;
		this.moment = moment;
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

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
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
