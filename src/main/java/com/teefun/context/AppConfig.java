package com.teefun.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.google.common.eventbus.EventBus;

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

	/**
	 * @return the properties place holder
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	/**
	 * @return the google event bus
	 */
	@Bean
	public EventBus getEventBus() {
		return new EventBus();
	}

}