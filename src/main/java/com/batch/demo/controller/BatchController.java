package com.batch.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BatchController {
    private final JobLauncher jobLauncher;
    private final Job job;

    @GetMapping("/start")
    public String getMethodName() {
        try{
            JobParameters jobParameters = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

            JobExecution jobExecution = jobLauncher.run(job, jobParameters);

            var batchStatus = jobExecution.getStatus();
            while (batchStatus.isRunning()) {
                log.info("Still running...");
                Thread.sleep(5000L);
            }
        }catch(Exception e){
            log.error("ERROR ", e);
        }
        return new String();
    }
    
}
