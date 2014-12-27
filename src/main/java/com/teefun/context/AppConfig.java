package com.teefun.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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
@ComponentScan(basePackages = "com.teefun")
@PropertySource("classpath:app.properties")
public class AppConfig {

	// TODO init csrf filters

}