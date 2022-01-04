package ticket.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import ticket.dao.OrderDao;
import ticket.lib.Inject;
import ticket.lib.Service;
import ticket.model.Order;
import ticket.model.ShoppingCart;
import ticket.model.Ticket;
import ticket.model.User;
import ticket.service.OrderService;
import ticket.service.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        List<Ticket> tickets = new ArrayList<>(shoppingCart.getTickets());
        order.setTickets(tickets);
        order.setUser(shoppingCart.getUser());
        order.setOrderDate(LocalDateTime.now());
        shoppingCartService.clearShoppingCart(shoppingCart);
        return orderDao.add(order);
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        return orderDao.getByUser(user);
    }
}
