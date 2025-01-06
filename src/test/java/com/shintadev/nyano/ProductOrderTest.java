package com.shintadev.nyano;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.shintadev.nyano.entity.Order;
import com.shintadev.nyano.entity.Product;
import com.shintadev.nyano.repository.OrderRepository;
import com.shintadev.nyano.repository.ProductRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ProductOrderTest {
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Test
  @Transactional
  @Rollback(false)
  void manyToManyInsertTest() {
    // Create product
    Product product1 = new Product();
    Product product2 = new Product();
    product1.setName("Product 1");
    product1.setPrice(new BigDecimal("100"));
    product2.setName("Product 2");
    product2.setPrice(new BigDecimal("200"));

    // Create order
    Order order1 = new Order();
    Order order2 = new Order();
    Order order3 = new Order();
    order1.setUserId(1);
    order2.setUserId(2);
    order3.setUserId(3);

    product1.setOrders(List.of(order1, order2));
    product2.setOrders(List.of(order2, order3, order1));

    orderRepository.save(order1);
    orderRepository.save(order2);
    orderRepository.save(order3);

    productRepository.save(product1);
    productRepository.save(product2);

  }

  @Test
  @Transactional
  @Rollback(false)
  void selectManyToManyTest() {
    Product product1 = productRepository.findById(1L).orElseThrow();
    Product product2 = productRepository.findById(2L).orElseThrow();

    System.out.println(product1);
    System.out.println(product1.getOrders());
    System.out.println(product2);
    System.out.println(product2.getOrders());

  }
}
