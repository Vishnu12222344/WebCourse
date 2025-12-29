package com.WebCourse.Controller;

import com.WebCourse.Model.Order;
import com.WebCourse.Service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    // CREATE ORDER
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return ordersService.saveOrder(order);
    }

    // GET ALL ORDERS
    @GetMapping
    public List<Order> getAllOrders() {
        return ordersService.getAllOrders();
    }

    // GET ORDER BY ID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Integer id) {
        return ordersService.getOrderById(id);
    }
}
