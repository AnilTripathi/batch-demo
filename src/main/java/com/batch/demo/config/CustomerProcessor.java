package com.batch.demo.config;

import org.springframework.batch.item.ItemProcessor;

import com.batch.demo.entity.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerProcessor implements ItemProcessor<Customer, Customer> {
    @Override
    public Customer process(Customer item) {
        log.info("customer records={}",item);
        return item;
    }
}