package com.example.demo;

import com.example.demo.sale.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class OrderDetailRepositoryTests {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testListAll(){
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        assertThat(orderDetails).isNotNull();
        for (OrderDetail orderDetail : orderDetails)
            System.out.println(orderDetail);
    }

    @Test
    public void testAddOrderDetail(){
        Product product = new Product();
        product.setName("Iphone 15");
        product.setPrice(1999);

        productRepository.save(product);

        Order order = new Order();
        order.setCustomerName("Nguy Duc Bao");
        order.setStatus("In progress");

        orderRepository.save(order);


        OrderDetailID orderDetailID = new OrderDetailID();
        orderDetailID.setOrder(order);
        orderDetailID.setProduct(product);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(orderDetailID);
        orderDetail.setQuantity(2);
        orderDetail.setUnitPrice(1000);
        orderDetail.setSubtotal(2000);

        OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);
        assertThat(savedOrderDetail).isNotNull();
        assertThat(savedOrderDetail.getId().getProduct()).isEqualTo(product);
        assertThat(savedOrderDetail.getId().getOrder()).isEqualTo(order);

    }
}
