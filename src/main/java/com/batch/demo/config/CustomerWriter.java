package com.batch.demo.config;

import org.springframework.batch.item.data.RepositoryItemWriter;

import com.batch.demo.entity.Customer;
import com.batch.demo.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CustomerWriter{
    private final CustomerRepository repository;

    public RepositoryItemWriter<Customer> itemWriter(){
		RepositoryItemWriter<Customer> writer=new RepositoryItemWriter<>();
		writer.setRepository(repository);
		writer.setMethodName("save");
		return writer;
	}
    
}