package com.empresa.ordering.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.ordering.entities.OrderItem;

/*Neste caso nao é necessario definir anotation, pois a classe criada ja esta erdando da classe
 * JpaRepository, que já é um componente Spring*/

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
