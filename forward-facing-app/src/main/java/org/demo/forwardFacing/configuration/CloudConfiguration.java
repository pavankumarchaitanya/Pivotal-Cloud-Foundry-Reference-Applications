package org.demo.forwardFacing.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("cloud")
public class CloudConfiguration {

	@Bean
	@LoadBalanced
	public RestTemplate loadBalancedRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
