/**
 *
 */
package com.teefun.context;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Databse configuration.
 *
 * @author Rajh
 *
 */
@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:db.properties" })
public class DataBaseConfig {

	/**
	 * Env for properties.
	 */
	@Resource
	private Environment env;

	/**
	 * @return the default data source
	 */
	@Bean
	public DataSource defaultDataSource() {
		final BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(this.env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(this.env.getProperty("jdbc.url"));
		dataSource.setUsername(this.env.getProperty("jdbc.user"));
		dataSource.setPassword(this.env.getProperty("jdbc.pass"));

		return dataSource;
	}

	/**
	 * @return the JPA entity manager
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

		factoryBean.setDataSource(this.defaultDataSource());
		factoryBean.setPackagesToScan(new String[] { "com.teeworlds.teefun.db" });

		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(true);
		// vendorAdapter.setGenerateDdl(generateDdl)

		factoryBean.setJpaVendorAdapter(vendorAdapter);

		factoryBean.setJpaProperties(this.hibernateProperties());

		return factoryBean;
	}

	/**
	 * @return the transaction manager
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(this.entityManagerFactoryBean().getObject());

		return transactionManager;
	}

	/**
	 * @return ExceptionTranslation
	 */
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	/**
	 * @return custom hibernate properties
	 */
	private Properties hibernateProperties() {
		return new Properties() {
			{
				this.setProperty("hibernate.hbm2ddl.auto", DataBaseConfig.this.env.getProperty("hibernate.hbm2ddl.auto"));
				this.setProperty("hibernate.dialect", DataBaseConfig.this.env.getProperty("hibernate.dialect"));
				this.setProperty("hibernate.globally_quoted_identifiers", "true");
			}
		};
	}

}
