package KitchenAPI;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class Handler {

    private static final ArrayList<Order> orders = new ArrayList<>();

    private static void add(Order order) {
        orders.add(order);
    }

    public static ArrayList getOrders() {
        return orders;
    }

    @PostMapping(value = "/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String get(@RequestBody Order order) {
        order.setEndPriority();
        System.out.println(order);
        add(order);
        return "200";
    }

}
