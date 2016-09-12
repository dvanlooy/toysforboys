package be.vdab.repositories;

import java.util.List;

import javax.persistence.LockModeType;

import be.vdab.entities.Order;

public class OrderRepository extends AbstractRepository {

	public List<Order> findAllUnshippedOrders(int vanafRij, int aantalRijen) {
		return getEntityManager().createNamedQuery("Order.findAllUnshippedOrders", Order.class).setFirstResult(vanafRij)
				.setMaxResults(aantalRijen).getResultList();
	}

	public Order read(long id) {
		return getEntityManager().find(Order.class, id);
	}

	public Order readWithLock(long id) {
		return getEntityManager().find(Order.class, id, LockModeType.PESSIMISTIC_WRITE);
	}
}
