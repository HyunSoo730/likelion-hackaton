package com.example.lionproject.Config;

import org.springframework.context.annotation.Configuration;

/**
 * https://stackoverflow.com/questions/65631547/spring-batch-postgres-error-relation-batch-job-instance-does-not-exist
 * -> Remove all @EnableBatchProcessing annotations to make sure jobs are executed
 * -> Updated after Spring Batch 5
 */
@Configuration
public class SpringBatchConfig {
}
