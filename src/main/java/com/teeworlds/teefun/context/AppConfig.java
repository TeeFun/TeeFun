package com.teeworlds.teefun.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Default app context.
 * 
 * @author Rajh
 * 
 */
@Configuration
@EnableAsync
@EnableScheduling
@ComponentScan(basePackages = "com.teeworlds.teefun")
public class AppConfig {

	// TODO init csrf filters
}