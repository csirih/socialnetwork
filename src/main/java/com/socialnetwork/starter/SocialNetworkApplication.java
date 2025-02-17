package com.socialnetwork.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collections;
@ComponentScan("com.socialnetwork")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class})
//@EnableAutoConfiguration
public class SocialNetworkApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SocialNetworkApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8087"));
		app.run(args);
		SpringApplication.run(SocialNetworkApplication.class, args);
		/*
		(basePackages = {
        "com.excelsystems.persistence"
})
		 */
	}

}
