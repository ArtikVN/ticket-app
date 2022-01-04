package ticket;

import ticket.exception.AuthenticationException;
import ticket.lib.Injector;
import ticket.model.*;
import ticket.security.AuthenticationService;
import ticket.service.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    private static final Injector injector = Injector.getInstance("ticket");

    public static void main(String[] args) {
        MovieService movieService =
                (MovieService) injector.getInstance(MovieService.class);

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setDescription("An action film about street racing, heists, and spies.");
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));
        movieService.getAll().forEach(System.out::println);

        CinemaHall firstCinemaHall = new CinemaHall();
        firstCinemaHall.setCapacity(100);
        firstCinemaHall.setDescription("first hall with capacity 100");

        CinemaHall secondCinemaHall = new CinemaHall();
        secondCinemaHall.setCapacity(200);
        secondCinemaHall.setDescription("second hall with capacity 200");

        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(firstCinemaHall);
        cinemaHallService.add(secondCinemaHall);

        System.out.println(cinemaHallService.getAll());
        System.out.println(cinemaHallService.get(firstCinemaHall.getId()));

        MovieSession tomorrowMovieSession = new MovieSession();
        tomorrowMovieSession.setCinemaHall(firstCinemaHall);
        tomorrowMovieSession.setMovie(fastAndFurious);
        tomorrowMovieSession.setShowTime(LocalDateTime.now().plusDays(1L));

        MovieSession yesterdayMovieSession = new MovieSession();
        yesterdayMovieSession.setCinemaHall(firstCinemaHall);
        yesterdayMovieSession.setMovie(fastAndFurious);
        yesterdayMovieSession.setShowTime(LocalDateTime.now().minusDays(1L));

        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(tomorrowMovieSession);
        movieSessionService.add(yesterdayMovieSession);

        System.out.println(movieSessionService.get(yesterdayMovieSession.getId()));
        System.out.println(movieSessionService.findAvailableSessions(
                        fastAndFurious.getId(), LocalDate.now()));

        AuthenticationService authenticationService = (AuthenticationService)
                injector.getInstance(AuthenticationService.class);
        User user = new User();
        user.setEmail("user@gmail.com");
        user.setPassword("123");

        user = authenticationService.register(user.getEmail(), user.getPassword());

        try {
            user = authenticationService.login("user@gmail.com", "123");
        } catch (AuthenticationException e) {
            System.out.println(e);
        }
        System.out.println(user);

        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        shoppingCartService.addSession(tomorrowMovieSession, shoppingCart.getUser());

        OrderService orderService =
                (OrderService) injector.getInstance(OrderService.class);

        orderService.completeOrder(shoppingCartService.getByUser(user));
        List<Order> orders = orderService.getOrdersHistory(user);
        System.out.println(orders);
    }
}
