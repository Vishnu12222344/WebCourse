package com.WebCourse.Service;

import com.WebCourse.Model.Course;
import com.WebCourse.Model.Order;
import com.WebCourse.Repositories.CourseRepository;
import com.WebCourse.Repositories.OrdersRepository;
import com.WebCourse.Middleware.CourseNotFoundException; // Assuming this exists based on CourseService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CourseRepository courseRepository;

    // COMPLETED CREATE ORDER
    public Order saveOrder(Order order) {
        Long courseId = order.getCourse().getCourseId();
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Cannot create order: Course not found with id: " + courseId));

        order.setCourse(course);

        if (order.getOrderDate() == null) {
            order.setOrderDate(LocalDate.now());
        }

        if (order.getAmountPaid() == null) {
            order.setAmountPaid(course.getPrice());
        }

        return ordersRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return ordersRepository.findAll();
    }

    // GET ORDER BY ID
    public Order getOrderById(Integer id) {
        return ordersRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found with id: " + id)
                );
    }
}