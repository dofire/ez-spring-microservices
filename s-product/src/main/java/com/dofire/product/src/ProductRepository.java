package com.dofire.product.src;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
}