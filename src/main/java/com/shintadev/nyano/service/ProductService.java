package com.shintadev.nyano.service;

import java.util.List;

import com.shintadev.nyano.entity.Product;

public interface ProductService {
  List<Product> findAll();

  Product findById(Long id);

  Product save(Product product);

  void deleteById(Long id);
}
