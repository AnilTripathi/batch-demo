package com.batch.demo.config;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

import com.batch.demo.entity.Customer;

public class CustomerReader implements ItemReader<FlatFileItemReader<Customer>> {
    private String[] header = {"CustomerId","FirstName","LastName","Company","City","Country","Phone1","Phone2","Email","SubscriptionDate","Website"};

    @Override
    public FlatFileItemReader<Customer> read() {
        FlatFileItemReader<Customer> reader=new  FlatFileItemReader<>();
		 reader.setResource(new FileSystemResource("src/main/resources/customers.csv"));
		 reader.setName("csv-reader");
		 reader.setLinesToSkip(1);
		 reader.setLineMapper(lineMapper());
		 return reader;
    }

    private LineMapper<Customer> lineMapper() 
	{
		DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter(",");
		tokenizer.setStrict(false);
		tokenizer.setNames(header);
		
		BeanWrapperFieldSetMapper<Customer> beanWrapper = new BeanWrapperFieldSetMapper<>();
		beanWrapper.setTargetType(Customer.class);
		
		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(beanWrapper);
		return lineMapper;
	}
}

