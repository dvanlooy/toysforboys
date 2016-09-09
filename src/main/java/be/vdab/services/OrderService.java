package be.vdab.services;

import java.util.List;

import be.vdab.entities.Order;
import be.vdab.exceptions.UnshippedException;
import be.vdab.repositories.OrderRepository;

public class OrderService extends AbstractService {
	private final OrderRepository orderRepository = new OrderRepository();

	public List<Order> findAllUnshippedOrders(int vanafRij, int aantalRijen) {
		return orderRepository.findAllUnshippedOrders(vanafRij, aantalRijen);
	}

	public Order findUnshippedOrder(long id) {
		return orderRepository.findUnshippedOrder(id);
	}

	public Order read(long id) {
		return orderRepository.read(id);
	}

	public boolean setAsShipped(long id) {
		beginTransaction();
		try {

			orderRepository.readWithLock(id).ship();
			commit();
			return true;
		} catch (UnshippedException ex) {
			rollback();
			return false;
		}
	}

}
