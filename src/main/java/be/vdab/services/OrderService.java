package be.vdab.services;

import java.util.List;

import be.vdab.entities.Order;
import be.vdab.repositories.OrderRepository;

public class OrderService extends AbstractService{
	private final OrderRepository orderRepository = new OrderRepository();
	
	
	public List<Order> findAllUnshippedOrders(int vanafRij, int aantalRijen) {
		return orderRepository.findAllUnshippedOrders(vanafRij, aantalRijen);
	}
	
	public Order read(long id) {
		return orderRepository.read(id);
	}
}
