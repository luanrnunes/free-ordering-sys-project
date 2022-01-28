package com.empresa.ordering.entities.enums;

public enum OrderStatus {
	
	/*Nos tipos ENUM e recomendado definir os numeros manualmente, nao deixar automatico
	 * para caso novas insercoes ou exclusoes, nao quebrar a sequencia numerica*/
	
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	/*Para os enumerados com numeros definidos manualmente, e necessario
	 * criar um construtor para o tipo enumerado, que deve ser private*/
	
	private int code;
	
	private OrderStatus(int code) {
		this.code = code;
	}
	
	/*Funcao publica para acessar o valor de code externamente*/
	public int getCode() {
		return code;
	}
	
	/*Metodo que percorre os orderstatus e retorna o valor de acordo com o numero
	 * passado*/

	public static OrderStatus valueOf(int code) {
		for (OrderStatus value : OrderStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid Order Status code");
	}
	
}
