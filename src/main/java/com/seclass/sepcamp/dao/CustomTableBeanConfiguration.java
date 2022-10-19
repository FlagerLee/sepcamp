package com.seclass.sepcamp.dao;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CustomTableConfiguration.class)
public class CustomTableBeanConfiguration {
}
