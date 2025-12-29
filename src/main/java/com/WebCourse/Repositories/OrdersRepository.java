package com.WebCourse.Repositories;

import com.WebCourse.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Order, Integer> {
}
