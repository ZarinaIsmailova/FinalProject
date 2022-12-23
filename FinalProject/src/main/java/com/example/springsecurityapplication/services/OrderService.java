package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Autowired
    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }
    // Данный метод позволяет получить пользователя по id
    public Order getOrderById(int id){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElse(null);
    }
    // Данный метод позволяет обновить данные пользователя
//    @Transactional
//    public void updateOrder(int id, Order order){
//        order.setId(id);
//        orderRepository.save(order);
//    }
    @Transactional
    public void updateOrderStatus( Order order) {
        orderRepository.save(order);
    }
    // Данный метод позволяет удалить пользовател по id
    @Transactional
    public void deleteOrder(int id){
        orderRepository.deleteById(id);
    }

    public List<Order> getOrderNumberEndingWith(String ending_with){
        List<Order> orders = orderRepository.findByNumberEndingWith(ending_with);
        return orders;
    }
}
