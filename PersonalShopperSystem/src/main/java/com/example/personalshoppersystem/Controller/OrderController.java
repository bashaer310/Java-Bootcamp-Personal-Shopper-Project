package com.example.personalshoppersystem.Controller;

import com.example.personalshoppersystem.DTO.OrderStatusDTO;
import com.example.personalshoppersystem.Model.Orders;
import com.example.personalshoppersystem.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/get")
    public ResponseEntity getAllOrders(){
        return ResponseEntity.status(200).body(orderService.getAllOrders());
    }


    @PostMapping("/add/{id}")
    public ResponseEntity addOrder(@RequestBody @Valid Orders order,@PathVariable Integer id ){
        orderService.addOrder(order, id);
        return ResponseEntity.status(200).body("Order added!");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable Integer id,@RequestBody @Valid Orders order){
        orderService.updateOrder(id, order);
        return ResponseEntity.status(200).body("Order updated!");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Integer id){
        orderService.deleteOrder(id);
        return ResponseEntity.status(200).body("Order deleted!");
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity getOrderById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(orderService.getOrderById(id));
    }

    @GetMapping("/getStatusById/{id}")
    public ResponseEntity getStatusById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(orderService.getStatusById(id));
    }

    @GetMapping("/sortByOldestOrders")
    public ResponseEntity sortByOldestOrders(){
        return ResponseEntity.status(200).body(orderService.sortByOldestOrders());
    }

    @GetMapping("/sortByLatestOrders")
    public ResponseEntity sortByLatestOrders(){
        return ResponseEntity.status(200).body(orderService.sortByLatestOrders());
    }

    @GetMapping("/getOrderByCustomerCity/{city}")
    public ResponseEntity getOrderByCustomerCity(@PathVariable String city){
        return ResponseEntity.status(200).body(orderService.getOrderByCustomerCity(city));
    }

    @GetMapping("/{shopper_Id}/accept/{order_Id}")
    public ResponseEntity AcceptOrder(@PathVariable Integer order_Id, @PathVariable Integer shopper_Id){
        orderService.AcceptOrder(order_Id, shopper_Id);
        return ResponseEntity.status(200).body("Order accepted");
    }

    @PutMapping("/updateStatus/{order_Id}")
    public ResponseEntity updateStatus(@PathVariable Integer order_Id, @RequestBody @Valid OrderStatusDTO orderStatusDTO){
        orderService.updateStatus(order_Id, orderStatusDTO);
        return ResponseEntity.status(200).body("Order completed");
    }

    @PutMapping("/receiveOrder/{order_Id}/{comment}")
    public ResponseEntity receiveOrder(@PathVariable Integer order_Id, @PathVariable String comment ){
        orderService.receiveOrder(order_Id, comment);
        return ResponseEntity.status(200).body("Order closed");
    }

    @PutMapping("/rattingShopper/{shopperId}/{customerId}/{num}")
    public ResponseEntity rattingShopper(@PathVariable Integer shopperId, @PathVariable Integer customerId,@PathVariable Double num ){
        orderService.rattingShopper(shopperId,customerId,num);
        return ResponseEntity.status(200).body("Shopper rated");
    }

    @GetMapping("getByCustomerUsername/{username}")
    public ResponseEntity getOrderByCustomerUsername(@PathVariable String username) {
        return ResponseEntity.status(200).body(orderService.getOrderByCustomerUsername(username));
    }
    @GetMapping("getByShopperUsername/{username}")
    public ResponseEntity getOrderByShopperUsername(@PathVariable String username) {
        return ResponseEntity.status(200).body(orderService.getOrderByPersonalShopperUsername(username));
    }
    @GetMapping("getByStatus/{status}")
    public ResponseEntity getOrderByStatus(@PathVariable String status) {
        return ResponseEntity.status(200).body(orderService.getOrderByStatus(status));
    }



}
