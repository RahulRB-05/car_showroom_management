package com.example.AutoHub.sales.repository;

import com.example.AutoHub.sales.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface
SalesRepository extends JpaRepository<SalesOrder,Long> {
}
