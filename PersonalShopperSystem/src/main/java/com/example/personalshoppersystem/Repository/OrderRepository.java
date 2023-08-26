package com.example.personalshoppersystem.Repository;

import com.example.personalshoppersystem.Model.Customer;
import com.example.personalshoppersystem.Model.Orders;
import com.example.personalshoppersystem.Model.PersonalShopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
    Orders findOrderById(Integer id);
    List<Orders> findOrderByStatus(String status);
    List<Orders> findOrderByCustomer(Customer customer);
    List<Orders> findOrderByPersonalShopper(PersonalShopper shopper);
    List<Orders> findOrdersByPersonalShopperAndCustomer(PersonalShopper shopper,Customer customer);
    List<Orders> findOrdersByCustomer_City(String city);
    @Query("select o from Orders o ORDER BY o.createdAt desc ")
    List<Orders> sortByLatestOrders();
    @Query("select o from Orders o ORDER BY o.createdAt asc")
    List<Orders> sortByOldestOrders();

}
