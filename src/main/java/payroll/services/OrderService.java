package payroll.services;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import payroll.domain.Order;

public interface OrderService {

    public abstract CollectionModel<EntityModel<Order>> getOrders();
    public abstract EntityModel<Order> getOrderById(Long id);
    public abstract ResponseEntity<EntityModel<Order>> createOrder(Order order);
    public abstract ResponseEntity<?> cancelOrder(Long id);
    public abstract ResponseEntity<?> completeOrder(Long id);

}
