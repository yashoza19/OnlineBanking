package com.neu.onlinebanking.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages="com.neu.onlinebanking")
@PropertySource("classpath:persistence-mysql.properties")
public class BankConfig {
	
		@Autowired
		private Environment env;

		private Logger logger = Logger.getLogger(getClass().getName());

		@Bean
		public ViewResolver viewResolver() {
			
			InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
			
			viewResolver.setPrefix("/WEB-INF/views/");
			viewResolver.setSuffix(".jsp");
			
			return viewResolver;
		}
		
		
		@Bean
		public DataSource securityDataSource() {
			

			ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

			try {
				securityDataSource.setDriverClass("com.mysql.jdbc.Driver");		
			}
			catch (PropertyVetoException exc) {
				throw new RuntimeException(exc);
			}
			

			logger.info("jdbc.url=" + env.getProperty("jdbc.url"));
			logger.info("jdbc.user=" + env.getProperty("jdbc.user"));
			

			securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
			securityDataSource.setUser(env.getProperty("jdbc.user"));
			securityDataSource.setPassword(env.getProperty("jdbc.password"));
			

			securityDataSource.setInitialPoolSize(
			getIntProperty("connection.pool.initialPoolSize"));

			securityDataSource.setMinPoolSize(
					getIntProperty("connection.pool.minPoolSize"));
			
			securityDataSource.setMaxPoolSize(
					getIntProperty("connection.pool.maxPoolSize"));
			
			securityDataSource.setMaxIdleTime(
					getIntProperty("connection.pool.maxIdleTime"));
					
			return securityDataSource;
		}

		private int getIntProperty(String propName) {
			
			String propVal = env.getProperty(propName);

			int intPropVal = Integer.parseInt(propVal);
			
			return intPropVal;
		}
		
		private Properties getHibernateProperties() {


			Properties props = new Properties();

			props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
			props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
			props.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
			props.setProperty("hibernate.enable_lazy_load_no_trans", env.getProperty("hibernate.enable_lazy_load_no_trans"));
			logger.info("jdbc.user=" + env.getProperty("hibernate.hbm2ddl.auto"));
		
			return props;				
		}

		
		@Bean
		public LocalSessionFactoryBean sessionFactory(){
			

			LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
			

			sessionFactory.setDataSource(securityDataSource());
			sessionFactory.setPackagesToScan(env.getProperty("hiberante.packagesToScan"));
			sessionFactory.setHibernateProperties(getHibernateProperties());
			
			return sessionFactory;
		}
		
		@Bean
		@Autowired
		public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

			HibernateTransactionManager txManager = new HibernateTransactionManager();
			txManager.setSessionFactory(sessionFactory);

			return txManager;
		}	
		

}
