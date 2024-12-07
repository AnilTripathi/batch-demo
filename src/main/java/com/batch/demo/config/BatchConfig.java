package com.batch.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.batch.demo.entity.Customer;
import com.batch.demo.repository.CustomerRepository;

@Configuration
public class BatchConfig {

    @Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private JobRepository jobRepo;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Bean
	public FlatFileItemReader<Customer> itemReader(){
		 return new CustomerReader().read();
	}

	@Bean
	public CustomerProcessor processCustomer(){
		return new CustomerProcessor();
	}
	
	@Bean
	public RepositoryItemWriter<Customer> itemWriter(){
		return new CustomerWriter(customerRepository).itemWriter();
	}
	
	@Bean
	public Step step(){
		return new StepBuilder("step-1", jobRepo).<Customer,Customer>
				chunk(500, transactionManager)
				.reader(itemReader())
				.processor(processCustomer())
				.writer(itemWriter())
				.build();
	}
	
	@Bean
	public Job job(){
		return (Job) new JobBuilder("customer-import", jobRepo)
				.start(step())
				.build();
	}
}

