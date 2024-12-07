package com.batch.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.batch.demo.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String>{   
}
