package ticket.service;

import ticket.model.Order;
import ticket.model.ShoppingCart;
import ticket.model.User;

import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
