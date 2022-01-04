package ticket.dao;

import ticket.model.Order;
import ticket.model.User;

import java.util.List;

public interface OrderDao {
    Order add(Order order);

    List<Order> getByUser(User user);
}
