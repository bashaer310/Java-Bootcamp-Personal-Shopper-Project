package com.example.personalshoppersystem.Service;

import com.example.personalshoppersystem.API.ApiException;
import com.example.personalshoppersystem.DTO.OrderStatusDTO;
import com.example.personalshoppersystem.Model.Customer;
import com.example.personalshoppersystem.Model.Orders;
import com.example.personalshoppersystem.Model.PersonalShopper;
import com.example.personalshoppersystem.Repository.CustomerRepository;
import com.example.personalshoppersystem.Repository.OrderRepository;
import com.example.personalshoppersystem.Repository.PersonalShopperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final PersonalShopperRepository personalShopperRepository;

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }


    public void addOrder(Orders order, Integer id) {
        Customer customer = customerRepository.findCustomerById(id);
        if (customer == null) {
            throw new ApiException("customer not found");
        }

        order.setCreatedAt(LocalDateTime.now());
        order.setCreatedBy(customer.getName());
        order.setCustomer(customer);

        customerRepository.save(customer);
        orderRepository.save(order);
    }


    public void updateOrder(Integer id, Orders order) {
        Orders order1 = orderRepository.findOrderById(id);

        if (order1 == null) {
            throw new ApiException("Order Id not found");
        }
        order1.setCategory(order.getCategory());
        order1.setProduct(order.getProduct());
        order1.setBudget(order.getBudget());

        orderRepository.save(order1);

    }

    public void deleteOrder(Integer id) {
        Orders order = orderRepository.findOrderById(id);

        if (order == null) {
            throw new ApiException("Order Id not found");
        }

        orderRepository.delete(order);
    }


    public Orders getOrderById(Integer id) {
        Orders order = orderRepository.findOrderById(id);

        if (order == null) {
            throw new ApiException("Order Id not found");
        }

        return order;
    }

    public String getStatusById(Integer id) {
        Orders order = orderRepository.findOrderById(id);

        if (order == null) {
            throw new ApiException("Order Id not found");
        }

        return order.getStatus();
    }

    public List<Orders> sortByLatestOrders() {
        List<Orders> orders = orderRepository.sortByLatestOrders();

        if (orders.isEmpty()) {
            throw new ApiException("No orders yet");
        }

        return orders;
    }

    public List<Orders> sortByOldestOrders() {
        List<Orders> orders = orderRepository.sortByOldestOrders();

        if (orders.isEmpty()) {
            throw new ApiException("No orders yet");
        }

        return orders;
    }


    public List<Orders> getOrderByCustomerCity(String customerCity) {

        List<Orders> orders = orderRepository.findOrdersByCustomer_City(customerCity);

        if ((orders.isEmpty())) {
            throw new ApiException("City has no orders");
        }

        return orders;
    }

    public void AcceptOrder(Integer order_Id, Integer shopper_Id) {
        Orders orders = orderRepository.findOrderById(order_Id);
        PersonalShopper personalShopper = personalShopperRepository.findPersonalShopperById(shopper_Id);

        if (orders == null || personalShopper == null) {
            throw new ApiException("order can't completed");
        }
        if (orders.getPersonalShopper() != null) {
            throw new ApiException("order is in progress");
        }

        if (orders.getStatus().equals("pending")) {

            orders.setPersonalShopper(personalShopper);
            orders.setStatus("In progress");

            if (orders.getCustomer().getCity().equals(personalShopper.getCity())) {
                orders.setServicePrice(200.0);
            }
            orders.setServicePrice(300.0);
            orderRepository.save(orders);
        } else throw new ApiException("order not available");
    }


    public void updateStatus(Integer order_Id, OrderStatusDTO orderStatusDTO) {
        Orders orders1 = orderRepository.findOrderById(order_Id);

        if (orders1 == null) {
            throw new ApiException("order id not found");
        }
        if (orders1.getStatus().equals("In progress")) {

            orders1.setIsFound(orderStatusDTO.getIsFound());
            orders1.setPrice(orderStatusDTO.getPrice());

            orderRepository.save(orders1);

            if (orderStatusDTO.getPrice() > orders1.getBudget()) {
                orders1.setStatus("Canceled");
                orderRepository.save(orders1);
                throw new ApiException("Price of product more than budget");
            }
            if (!orderStatusDTO.getIsFound()) {
                orders1.setStatus("Canceled");
                orderRepository.save(orders1);
                throw new ApiException("Product not found");
            }
            orders1.setStatus("Completed");
            orderRepository.save(orders1);
        } else throw new ApiException("order is not available");
    }


    public void receiveOrder(Integer orderId, String comment) {
        Orders orders = orderRepository.findOrderById(orderId);
        PersonalShopper shopper = personalShopperRepository.findPersonalShopperById(orders.getPersonalShopper().getId());
        Customer customer = customerRepository.findCustomerById(orders.getCustomer().getId());
        if (orders == null) {
            throw new ApiException("order id not found");
        }

        if (orders.getStatus().equals("Completed")) {
            orders.setComment(comment);
            orders.setStatus("Closed");

            customer.setLoyaltyPoints(customer.getLoyaltyPoints() + 1);

            double newBalance = (0.8 * orders.getServicePrice()) + shopper.getPersonalShopperDetails().getBalance();
            shopper.getPersonalShopperDetails().setBalance(newBalance);

            orderRepository.save(orders);
            customerRepository.save(customer);
            personalShopperRepository.save(shopper);
        } else throw new ApiException("order is not available");

    }

    public void rattingShopper(Integer shopperId, Integer customerId, double number) {
        Customer customer = customerRepository.findCustomerById(customerId);
        PersonalShopper shopper = personalShopperRepository.findPersonalShopperById(shopperId);
        List<Orders> myOrders = orderRepository.findOrdersByPersonalShopperAndCustomer(shopper, customer);
        if (myOrders == null || (number < 1 && number > 5))
            throw new ApiException("Can't rate");

        Double rattingPercent = number / 5.0;
        shopper.getPersonalShopperDetails().setRating(shopper.getPersonalShopperDetails().getRating() + rattingPercent);

        personalShopperRepository.save(shopper);
    }

    public List<Orders> getOrderByStatus(String status) {
        List<Orders> Orders = orderRepository.findOrderByStatus(status);
        if (Orders == null)
            throw new ApiException("Orders not found");

        return Orders;
    }

    public List<Orders> getOrderByCustomerUsername(String username) {
        Customer customer = customerRepository.findCustomerByUsername(username);
        List<Orders> orders = orderRepository.findOrderByCustomer(customer);

        if (customer == null || orders == null)
            throw new ApiException("Orders not found");

        return orders;
    }

    public List<Orders> getOrderByPersonalShopperUsername(String username) {
        PersonalShopper shopper=personalShopperRepository.findPersonalShopperByUsername(username);
        List<Orders> orders = orderRepository.findOrderByPersonalShopper(shopper);


        if (shopper == null || orders == null)
            throw new ApiException("Orders not found");

        return orders;


    }
}