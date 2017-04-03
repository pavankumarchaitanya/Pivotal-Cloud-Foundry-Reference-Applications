package org.demo.forwardFacing;

import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
@EnableDiscoveryClient
// @EnableCircuitBreaker
public class Application implements ApplicationContextAware {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public static Logger logger = org.slf4j.LoggerFactory.getLogger(Application.class);

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {

		for (String beanname : arg0.getBeanDefinitionNames())
			logger.info("beanname ---------------------------: " + beanname);

	}

}
